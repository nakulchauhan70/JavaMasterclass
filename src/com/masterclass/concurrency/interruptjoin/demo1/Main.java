package com.masterclass.concurrency.interruptjoin.demo1;


import static com.masterclass.concurrency.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        final Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");
        anotherThread.start();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        anotherThread.interrupt();

        new Thread() {
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of run()");
                try {
                    anotherThread.join();
//                    anotherThread.join(2000); // wait only for 2 seconds and then will continue with is execution, remember anyway another thread will execute even after 2 sec.
//                    anotherThread.join();
                    anotherThread.interrupt(); // won't work immediately after join(), because join will allow another thread to execute it's task. join(2000) will perform interrupted exception on another thread
                    System.out.println(ANSI_RED + "AnotherThread terminated, or timed out, so I'm running again");
                } catch (InterruptedException e) { //required for join, but not for interrupted
                    System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
                }
            }
        });

        myRunnableThread.start();

        System.out.println(ANSI_PURPLE + "Hello again from the main thread.");
    }
}
//Purpose of interrupt - when we want to stop a thread what it is doing and make them do another task
//Purpose of join - when we want another thread to join our current thread, we use join and here thread on which join is called is run and current thread wait till completion of another thread.