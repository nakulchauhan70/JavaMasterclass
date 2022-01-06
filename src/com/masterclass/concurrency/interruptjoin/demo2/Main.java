package com.masterclass.concurrency.interruptjoin.demo2;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        thread.interrupt();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("I got interrupted");
        }
        System.out.println("in run");
    }
}