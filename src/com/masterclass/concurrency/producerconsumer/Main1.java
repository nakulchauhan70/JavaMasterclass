package com.masterclass.concurrency.producerconsumer;

import java.util.Random;

public class Main1 {

    // wait(), notify(), notifyAll() - methods can be called only in synchronized code - best example to demonstrate this - Producer consumer problem
    public static void main(String[] args) {
        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while (empty) {
            try {
                System.out.println(Thread.currentThread().getName() + " calling wait() from read");
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = true;
        System.out.println(Thread.currentThread().getName() + " calling notifyall from read");
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while (!empty) {
            try {
                System.out.println(Thread.currentThread().getName() + " calling wait() from write");
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = false;
        this.message = message;
        System.out.println(Thread.currentThread().getName() + " calling notifyall from write");
        notifyAll();
    }
}

class Writer implements Runnable {
    private final Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        String[] messages = {
                "Humpty Dumpty sat on a wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"
        };

        Random random = new Random();

        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable {
    private final Message message;

    public Reader(Message message) {
        this.message = message;
    }

    public void run() {
        Random random = new Random();
        for (String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
    }
}















