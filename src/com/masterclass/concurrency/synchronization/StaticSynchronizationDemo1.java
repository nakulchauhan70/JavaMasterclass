package com.masterclass.concurrency.synchronization;

public class StaticSynchronizationDemo1 {
    public static void main(String[] args) {
        new Thread(() -> {
            Thread.currentThread().setName("LinkedIn");
            Account.showAccount("linkedin/in/nakulchauhan70");
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Github");
            Account.showAccount("github.com/nakulchauhan70");
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Facebook");
            Account.showAccount("NC");
        }).start();

    }
}


class Account {
    synchronized static void showAccount(String accountName) {
        System.out.println("My account name is " + accountName + " Holder Name is " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    //equivalent code
//    static void showAccount(String accountName) {
//        synchronized (Account.class) {       // Synchronized block on class A
//            // ...
//        }
//    }
}