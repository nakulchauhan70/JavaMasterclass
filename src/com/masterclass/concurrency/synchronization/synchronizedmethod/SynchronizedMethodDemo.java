package com.masterclass.concurrency.synchronization.synchronizedmethod;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0;

    // Here synchronized is associated with Shopper class but not specific instance of shopper class
    // By doing so, when either thread invokes the synchronized addGarlic method, it will acquire the intrinsic lock that's associated with the class object.
    // If static keyword removed, then each of the two shopper threads will invoke their own instance of the addGarlic method which is associated with their own object's intrinsic lock.
    private static synchronized void addGarlic() {
        garlicCount++;
    }

    public void run() {
        for (int i = 0; i < 10_000_000; i++)
            addGarlic();
    }
}

public class SynchronizedMethodDemo {
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