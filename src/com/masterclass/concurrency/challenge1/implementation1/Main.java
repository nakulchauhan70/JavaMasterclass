package com.masterclass.concurrency.challenge1.implementation1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount("ABC-123", 1000);

        Thread t1 = new Thread(() -> {
            bankAccount.deposit(300);
            bankAccount.withdraw(50);
        });

        t1.start(); //bankBalance = 1000 + 300 - 50 = 1250

        Thread t2 = new Thread(() -> {
            bankAccount.deposit(203.75);
            bankAccount.withdraw(100);
        });

        t2.start(); //bankBalance = 1250 + 203.75 - 100 = 1353.75

//        t1.join();
//        t2.join();

        System.out.println(bankAccount);
    }
}
