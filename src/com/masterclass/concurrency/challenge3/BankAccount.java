package com.masterclass.concurrency.challenge3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance;
    private String accountNumber;
    private Lock reentrantLock;

    public BankAccount(String accountNumber, double initialBalance, ReentrantLock reentrantLock) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.reentrantLock = reentrantLock;
    }

//    public void deposit(double amount) {
//        reentrantLock.lock();
//        try {
//            balance += amount;
//        } finally {
//            reentrantLock.unlock();
//        }
//    }
//
//    public void withdraw(double amount) {
//        reentrantLock.lock();
//        try {
//            balance -= amount;
//        } finally {
//            reentrantLock.unlock();
//        }
//    }

    public void deposit(double amount) {
        try {
            if (reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                System.out.println("Unable to acquire lock while depositing");
            }
        } catch (InterruptedException e) {

        }
    }

    public void withdraw(double amount) {
        try {
            if (reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                System.out.println("Unable to acquire lock while withdrawing");
            }
        } catch (InterruptedException e) {

        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account no : " + accountNumber);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}