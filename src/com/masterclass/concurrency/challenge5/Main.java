package com.masterclass.concurrency.challenge5;

public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("12345-6789", 500.00);
        BankAccount account2 = new BankAccount("ABCDE-FGHI", 1500.00);

        new Thread(new Transfer(account1, account2, 100.00), "Transfer1").start();
        new Thread(new Transfer(account2, account1, 50.00), "Transfer2").start();
    }
}
