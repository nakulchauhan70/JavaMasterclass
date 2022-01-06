package com.masterclass.concurrency.synchronization;

public class StaticSynchronizationDemo2 {
    public static void main(String[] args) {
        new Thread(() -> {
            Table.printTable(1);
        }).start();

        new Thread(() -> {
            Table.printTable(10);
        }).start();

        new Thread(() -> {
            Table.printTable(100);
        }).start();
    }
}


class Table {
    synchronized static void printTable(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
            }
        }
    }
}

/*
Various ways for class level locking
public class DemoClass
{
    //Method is static
    public synchronized static void demoMethod(){

    }
}

or

public class DemoClass
{
    public void demoMethod()
    {
        //Acquire lock on .class reference
        synchronized (DemoClass.class)
        {
            //other thread safe code
        }
    }
}

or

public class DemoClass
{
    private final static Object lock = new Object();

    public void demoMethod()
    {
        //Lock object is static
        synchronized (lock)
        {
            //other thread safe code
        }
    }
}*/
