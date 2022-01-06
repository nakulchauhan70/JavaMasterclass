package com.masterclass.concurrency.challenge3;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        BankAccount bankAccount = new BankAccount("ABC-123", 1000, reentrantLock);

        new Thread(() -> {
            bankAccount.deposit(300);
            bankAccount.withdraw(50);
        }).start();

        new Thread(() -> {
            bankAccount.deposit(203.75);
            bankAccount.withdraw(100);
        }).start();

        System.out.println(bankAccount);
    }
}
