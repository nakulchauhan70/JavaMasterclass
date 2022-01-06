package com.masterclass.concurrency.producerconsumer;

public class Example1 {
    public static void main(String[] args) {
        Details details = new Details();
        Producer producer = new Producer(details);
        Consumer consumer = new Consumer(details);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}


class Details {
    int num;
    boolean valueSet = false;

    public synchronized void put() {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        num += 1;
        valueSet = true;
        System.out.println("Put::" + num);
        notify();
    }

    public synchronized void get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Get::" + num);
        valueSet = false;
        notify();
    }
}

class Producer implements Runnable {
    Details details;

    Producer(Details details) {
        this.details = details;
//        Thread thread = new Thread(this, "producer");
//        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            details.put();
        }
    }
}

class Consumer implements Runnable {
    Details details;

    Consumer(Details details) {
        this.details = details;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            details.get();
        }
    }
}

// If for loop count is mismatched like producer looping for 10 times and consumer looping times
// then after 6 time producer will wait for consumer forever.