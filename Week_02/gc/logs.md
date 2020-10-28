- 串行 GC

  - 128m

    ```powershell
  java -XX:+UseSerialGC -Xms128m -Xmx128m -Xloggc:gc.serial128.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            at java.util.Arrays.copyOfRange(Unknown Source)
          at java.lang.String.<init>(Unknown Source)
            at java.lang.StringBuilder.toString(Unknown Source)
        at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:58)
            at GCLogAnalysis.main(GCLogAnalysis.java:25)
```
    
[gc.serial128.log](gc.serial128.log)
    
  
    
  - 256m
  
  ```powershell
    java -XX:+UseSerialGC -Xms256m -Xmx256m -Xloggc:gc.serial256.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
  正在执行...
    执行结束!共生成对象次数:4680
  ```
  
  [gc.serial256.log](gc.serial256.log)
  
    
  
  - 512m
  
    ```powershell
    java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.serial512.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:10064
    ```

    [gc.serial512.log](gc.serial512.log)

    

- 并行 GC

  - 256m

    ```powershell
    java -XX:+UseParallelGC -Xms256m -Xmx256m -Xloggc:gc.parallel256.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:42)
            at GCLogAnalysis.main(GCLogAnalysis.java:25)
    ```

    [gc.parallel256.log](gc.parallel256.log)

    

  - 512m

    ```powershell
    java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.parallel512.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:8640
    ```

    [gc.parallel512.log](gc.parallel512.log)

    

  - 1024m

    ```powershell
    java -XX:+UseParallelGC -Xms1024m -Xmx1024m -Xloggc:gc.parallel1024.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:8560
    ```

    [gc.parallel1024.log](gc.parallel1024.log)

    
    
  - 4096m

    ```powershell
    java -XX:+UseParallelGC -Xms4096m -Xmx4096m -Xloggc:gc.parallel4096.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:3991
    ```

    [gc.parallel4096.log](gc.parallel4096.log)

    

- CMS GC

  - 512m

    ```powershell
    java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:gc.cms512.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:9528
    ```

    [gc.cms512.log](gc.cms512.log)

    

  - 4096m

    ```powershell
    java -XX:+UseConcMarkSweepGC -Xms4096m -Xmx4096m -Xloggc:gc.cms4096.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:10319
    ```

    [gc.cms4096.log](gc.cms4096.log)

    

- G1 GC

  - 512m

    ```powershell
    java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.gone512.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:10111
    ```

    [gc.gone512.log](gc.gone512.log)

    

  - 4096m

    ```powershell
    java -XX:+UseG1GC -Xms4096m -Xmx4096m -Xloggc:gc.gone4096.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
    正在执行...
    执行结束!共生成对象次数:11531
    ```

    [gc.gone4096.log](gc.gone4096.log)

    