package com.masterclass.concurrency.synchronization;

import com.masterclass.concurrency.ThreadColor;

//https://howtodoinjava.com/java/multi-threading/object-vs-class-level-locking/
public class Main {

    // Synchronization - The process of controlling when thread execute code when they can access the heap is called synchronization
    // Process of avoiding race condition
    // Lock in object class is intrinsic lock.
    // Intrinsic lock - a thread can acquire a lock it already owns
    // When a class/method is synchronized - means developer has taken care of all critical section in class/method
    // Object level lock is mechanism when we want to synchronize a non-static method or non-static code block such that only one thread will be able to execute the code block on given instance of the class. This should always be done to make instance level data thread safe.
    public static void main(String[] args) {
        //No interference if we pass countdown1, countdown2 in CountdownThread
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

    private int i;

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

        synchronized (this) { //synchronized (color) - we'll get thread interference - irregular output - it is because of local variable and local variable are stored in thread stack, each thread stack will work independently, synchronized will on shared object ie. object from heap area
            for (i = 10; i > 0; i--) {  //int i without synchronized will give irregular output 20 times. i without synchronization will give irregular output 10-11 time.
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
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

/*
Various ways for object level locking

public class DemoClass
{
    public synchronized void demoMethod(){}
}

or

public class DemoClass
{
    public void demoMethod(){
        synchronized (this)
        {
            //other thread safe code
        }
    }
}

or

public class DemoClass
{
    private final Object lock = new Object();
    public void demoMethod(){
        synchronized (lock)
        {
            //other thread safe code
        }
    }
}*/
