package com.masterclass.concurrency.synchronization.datarace.datarace;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0;

    //Because of much suspension point we won't get final garlicCount as 2 million
    public void run() {
        for (int i = 0; i < 10_000_000; i++)
            garlicCount++;
    }

//    static Set<Integer> set = new HashSet<>();
//    public void run() {
//        for (int i = 0; i < 10; i++)
//            set.add(i);
//    }
}

public class DataRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
//        System.out.println(Shopper.set);
    }
}