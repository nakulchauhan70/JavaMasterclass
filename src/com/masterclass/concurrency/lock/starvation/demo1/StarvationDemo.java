package com.masterclass.concurrency.lock.starvation.demo1;

import com.masterclass.concurrency.ThreadColor;

//ThreadPriority is a suggestion to OS to run in given order, but it is not binding system to run in given order
//Setting thread priority can make starvation more likely to happen
//Dealing with deadlocks - the order in which locks are required is important
//Dealing with starvation - which thread gets to run when a lock becomes available is important
public class StarvationDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t3.start();
        t2.start();
        t5.start();
        t4.start();
        t1.start();

    }

    private static class Worker implements Runnable {
        private final String threadColor;
        private int runCount = 1;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
//                synchronized (lock) {
                System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                // execute critial section of code
//                }
            }
        }
    }
}
