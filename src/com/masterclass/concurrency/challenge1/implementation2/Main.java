package com.masterclass.concurrency.challenge1.implementation2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount("ABC-123", 1000);

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
