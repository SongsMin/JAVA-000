package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class SimpleFilter implements HttpRequestFilter {
    private final String name;

    public SimpleFilter(String name) {
        this.name = name;
    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().add("nio", name);
    }
}
