package com.masterclass.concurrency.lock.deadlock;

public class DiningPhilosopher {
    public static void main(String[] args) throws Exception {

        Philosopherr[] philosophers = new Philosopherr[500];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosopherr(leftFork, rightFork);

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}


class Philosopherr implements Runnable {

    private Object leftFork;
    private Object rightFork;
    static int i = 5;

    public Philosopherr(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (i > -1) {
                // thinking
                doAction(System.nanoTime() + ": Thinking");
                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": Picked up left fork");
                    synchronized (rightFork) {
                        // eating
                        doAction(System.nanoTime() + ": Picked up right fork - eating");
                        doAction(System.nanoTime() + ": Put down right fork");
                    }

                    // Back to thinking
                    doAction(System.nanoTime() + ": Put down left fork. Back to thinking");
                }
                i--;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
}