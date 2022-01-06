package com.masterclass.concurrency.synchronization.synchronizedstatement;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0;

    public void run() {
        for (int i = 0; i < 10_000_000; i++)
            synchronized (Shopper.class) { //synchronized(this) won't work - this represents each instance of Shopper class
                garlicCount++;
            }
    }

//    static Integer garlicCount = 0;
//
//    public void run() {
//        for (int i = 0; i < 10_000_000; i++)
//            // making use of Integer class instead of int and using in synchronized like synchronized(garlicCount) will not work because Integer class is immutable.
//            // So once you create Integer object you are not allowed to change it. Every time java instantiates a new integer object which will have different. So each time in loop java creates new object.
//            // This uses different intrinsic lock
//            synchronized (garlicCount) {
//                garlicCount++;
//            }
//    }
}

public class SynchronizedStatementDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    }
}