package com.masterclass.concurrency.forkjoinpool;

import java.util.stream.IntStream;

//https://www.javacodemonk.com/java-8-parallel-stream-custom-threadpool-48643a91
public class CustomThreadPool {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        IntStream s = IntStream.range(0, 20);
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        s.parallel().forEach(i -> {
            try {
                Thread.sleep(100);
            } catch (Exception ignore) {
            }
            System.out.print((System.currentTimeMillis() - start) + " ms");
        });
        System.out.println("\nOverall time consumed: " + (System.currentTimeMillis() - start) + " ms");
    }
}
