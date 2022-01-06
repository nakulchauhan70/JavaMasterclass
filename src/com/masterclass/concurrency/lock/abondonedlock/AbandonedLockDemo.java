package com.masterclass.concurrency.lock.abondonedlock; /**
 * Three philosophers, thinking and eating sushi
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {

    private static int sushiCount = 1000;
    private Lock firstChopstick, secondChopstick;

    public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        while (sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            // take a piece of sushi
            if (sushiCount > 0) {
                sushiCount--;
                System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
            }

            //this will make program terminate and another thread will wait to get lock
            //resolution - wrap critical section of code in try catch
            if (sushiCount == 0) {
                System.out.println(1 / 0);
            }

            // put down chopsticks
            secondChopstick.unlock();
            firstChopstick.unlock();
        }
    }
}

public class AbandonedLockDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher("Barron", chopstickA, chopstickB).start();
        new Philosopher("Olivia", chopstickB, chopstickC).start();
        new Philosopher("Steve", chopstickA, chopstickC).start();
    }
}