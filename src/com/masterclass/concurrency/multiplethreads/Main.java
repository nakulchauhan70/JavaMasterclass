package com.masterclass.concurrency.multiplethreads;

import com.masterclass.concurrency.ThreadColor;

public class Main {

    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class Countdown {

    int i; //with this instance variable result will be printed 11 times, with i = 10 twice
    //Heap area is shared with multiple threads
    //In this application multiple threads are working on same object created in heap area
    //So when one object's instance variable changed by one thread, next thread on this same object will get changed instance variable only and process

    public void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }


        // because local variable store in stack and stack area is not shared by multiple thread.
        // Hence, each thread will use it's own local variable and process it
        // Here, one thread can be suspended at any point or step, and then another thread can work
        // Potential suspension point where thread can be suspended can be - 1. just before decrementing i, 2. just before checking condition, 3. just before printing out. or 4. string concat and many more
        // Here two threads thought initial value as 10, one thread got suspended before decrementing value from 10 to 9,
        // but second thread decremented it and then next time any of two thread got 9, and they got suspended after printing or after decrementing or any potential suspension point.
        // Hence, we got two times 10 but others only one time with like Thread 1 printing 4 times or Thread 2 printing 6 times.
        // Here thread 1 decremented value
        for (i = 10; i > 0; i--) {  // By defining int here(or local variable), two threads will run 10 times each
            System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
        }
    }
}

class CountdownThread extends Thread {
    private final Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }
}