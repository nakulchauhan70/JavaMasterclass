package com.masterclass.concurrency.synchronization.datarace.mutualexclusion; /**
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shopper extends Thread {

    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock(); //by making this as instance variable we won't get 2 million garlic count, even though using reentrant lock

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            pencil.lock();
            garlicCount++;  //only this part of code requires mutual exclusion
            pencil.unlock();
//            System.out.println(Thread.currentThread().getName() + " is thinking.");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

public class MutualExclusionDemo {
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