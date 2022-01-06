package com.masterclass.concurrency.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

// ArrayBlockingQueue is a bounded BlockingQueue backed by an array.
// Here, bounded means the size of the Queue is finite and fixed.
// Once created, we cannot grow or shrink the size of the Queue.
// If we try to insert an element into a full Queue then it will result in the operation blocking.
// Similarly, if we try to take an element from an empty Queue, then also the operation will be blocked.
// ArrayBlockingQueue stores the elements in the Queue internally in the FIFO (first-in-first-out) order.
public class ArrayBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> priorityBlockingQueue = new ArrayBlockingQueue<>(5);

        //Producer thread
        new Thread(() ->
        {
            int i = 0;
            try {
                while (true) {
                    priorityBlockingQueue.put(++i);
                    System.out.println("Added : " + i);

                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        //Consumer thread
        new Thread(() ->
        {
            try {
                while (true) {
                    Integer poll = priorityBlockingQueue.take();
                    System.out.println("Polled : " + poll);

                    Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
