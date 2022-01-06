package com.masterclass.concurrency.forkjoinpool;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;
import static java.util.stream.Collectors.toList;

//It implements the work-stealing algorithm (i.e., worker threads that run out of things to do can steal tasks from other threads that are still busy)
public class CheckPrimeNo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4); // Configure the number of threads
        List<Integer> integers = forkJoinPool.submit(() -> IntStream.range(1, 1_000_000)
                        .parallel()
                        .filter(CheckPrimeNo::isPrime).boxed()
                        .collect(toList()))
                .get();

        System.out.println(integers);
        forkJoinPool.shutdown();
    }

    private static boolean isPrime(long n) {
        return n > 1 && IntStream.rangeClosed(2, (int) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }
}