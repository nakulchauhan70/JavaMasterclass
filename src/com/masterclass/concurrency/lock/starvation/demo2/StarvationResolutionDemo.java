package com.masterclass.concurrency.lock.starvation.demo2;
/**
 * Three philosophers, thinking and eating sushi
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher1 extends Thread {

    private static int sushiCount = 500_000;
    private Lock firstChopstick, secondChopstick;

    public Philosopher1(String name, Lock firstChopstick, Lock secondChopstick) {
        this.setName(name);
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    public void run() {
        while (sushiCount > 0) { // eat sushi until it's all gone

            // pick up chopsticks
            firstChopstick.lock();
            secondChopstick.lock();

            try {
                // take a piece of sushi
                if (sushiCount > 0) {
                    sushiCount--;
                    System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // put down chopsticks
                secondChopstick.unlock();
                firstChopstick.unlock();
            }
        }
    }
}

public class StarvationResolutionDemo {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();
        new Philosopher1("Barron", chopstickA, chopstickB).start();
        new Philosopher1("Olivia", chopstickB, chopstickC).start();
        new Philosopher1("Steve", chopstickA, chopstickC).start();
    }
}