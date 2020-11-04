package io.github.kimmking.gateway.outbound.okhttp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler {
    private final OkHttpClient httpClient;
    private final ExecutorService proxyService;
    private final String backendUrl;
    public OkhttpOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/")?backendUrl.substring(0,backendUrl.length()-1):backendUrl;

        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

        httpClient = new OkHttpClient();
    }
    public void handle(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext context) {
        final String uri = backendUrl + fullHttpRequest.uri();
        proxyService.submit(() -> fetchGet(fullHttpRequest, context, uri));
    }

    private void fetchGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext context, String uri) {
        final Request request = new Request.Builder()
                .url(uri)
                .get()
                .build();
        Call call = httpClient.newCall(request);
        try {
            Response endpointResponse = call.execute();

            FullHttpResponse response = null;
            try {
                response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(endpointResponse.body().bytes()));
                response.headers().set("Content-Type", "application/json");
                response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.header("Content-Length")));
            } catch (Exception e) {
                e.printStackTrace();
                response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
                exceptionCaught(context, e);
            } finally {
                if (fullHttpRequest != null) {
                    if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                        context.write(response).addListener(ChannelFutureListener.CLOSE);
                    } else {
                        context.write(response);
                    }
                }
                context.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exceptionCaught(ChannelHandlerContext context, Exception e) {
        e.printStackTrace();
        context.close();
    }
}
