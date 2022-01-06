package com.masterclass.concurrency.lock.starvation.demo1;

import com.masterclass.concurrency.ThreadColor;

import java.util.concurrent.locks.ReentrantLock;

//Fair lock - alternative to synchronization
//Live lock - similar to deadlock, but instead of the threads blocked they are actually constantly active and usually waiting for all the other threads to complete their tasks.
//Since all the threads are waiting others to complete, none of them progesses
public class StarvationResolutionDemo {
    //With this fairness in acquiring lock is guaranteed not fairness in thread scheduling, Only it guarantees
    //Performance will be impacted while using Reentrant lock with lots of threads
    //tryLock() method doesn't honor first come first serve
    private static final ReentrantLock lock = new ReentrantLock(true); //true means fair lock - First come first server ordering for getting lock, by default is false

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
                lock.lock();
                try {
                    System.out.format(threadColor + "%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                    // execute critical section of code
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
