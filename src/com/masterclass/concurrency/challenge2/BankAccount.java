package com.masterclass.concurrency.challenge2;

class BankAccount {
    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        synchronized (this) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        synchronized (this) {
            balance -= amount;
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


// This was a bit of a trick challenge
// we'd actually don't have to make any changes to the code
// and that's because both threads only read the account number
// so thread interference isn't an issue now it would actually be a mistake to synchronize the get account number
// and print account number methods in this scenario and that's
// because would be over synchronizing the code and applications
// with a large number of threads that can have a noticeably negative impact on performance