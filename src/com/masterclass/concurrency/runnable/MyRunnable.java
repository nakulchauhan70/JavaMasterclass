package com.masterclass.concurrency.runnable;

import com.masterclass.concurrency.ThreadColor;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_RED + "Hello from MyRunnable's implementation of run()");
    }
}
