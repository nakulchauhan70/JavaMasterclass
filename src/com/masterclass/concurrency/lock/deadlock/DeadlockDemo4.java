package com.masterclass.concurrency.lock.deadlock;

public class DeadlockDemo4 {
    public static void main(String[] args) throws Exception {

        Philosopherrr[] philosophers = new Philosopherrr[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == philosophers.length - 1) {
                // The last philosopher picks up the right fork first
                philosophers[i] = new Philosopherrr(rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopherrr(leftFork, rightFork);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}


class Philosopherrr implements Runnable {

    private Object leftFork;
    private Object rightFork;
//    private static int i = 100;

    public Philosopherrr(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
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
//                i--;
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