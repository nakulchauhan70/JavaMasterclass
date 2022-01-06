package com.masterclass.concurrency.lock.readwritelock;
/**
 * Several users reading a calendar, but only a few users updating it
 */

import java.util.concurrent.locks.ReentrantLock;

//Issue with below code - Single reentrant lock to protect both critical sections, only one thread can ever be reading or writing at a time.
class CalendarUserWithoutClass extends Thread {

    private static final String[] WEEKDAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static final ReentrantLock marker = new ReentrantLock();
    private static int today = 0;
//    private static Lock readMarker = marker.readLock();
//    private static Lock writeMarker = marker.writeLock();

    public CalendarUserWithoutClass(String name) {
        this.setName(name);
    }

    public void run() {
        while (today < WEEKDAYS.length - 1) {
            if (this.getName().contains("Writer")) { // update the shared calendar
                marker.lock();
                try {
                    today = (today + 1) % 7;
                    System.out.println(this.getName() + " updated date to " + WEEKDAYS[today]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                {
                    marker.unlock();
                }
            } else { // Reader - check to see what today is
                marker.lock();
                try {
                    System.out.println(this.getName() + " sees that today is " + WEEKDAYS[today]);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    marker.unlock();
                }
            }
        }
    }
}

public class ReadWriteLockWithoutClassDemo {
    public static void main(String[] args) {
        // create ten reader threads
        for (int i = 0; i < 10; i++)
            new CalendarUserWithoutClass("Reader-" + i).start();

        // ...but only two writer threads
        for (int i = 0; i < 2; i++)
            new CalendarUserWithoutClass("Writer-" + i).start();
    }
}