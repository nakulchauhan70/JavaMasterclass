package com.masterclass.concurrency.threadinterference;

import com.masterclass.concurrency.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}

class MyProducer implements Runnable {
    private final List<String> buffer;
    private final String color;

    public MyProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color + "Adding..." + num);  // producer can be suspended while printing, hence not included in synchronized
                synchronized (buffer) {
                    buffer.add(num);                            // here producer is sleeping after adding into buffer
                }

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting....");
        synchronized (buffer) {
            buffer.add("EOF");                  // producer can be suspended while printing, hence not included in synchronized
        }
    }
}

class MyConsumer implements Runnable {
    private final List<String> buffer;
    private final String color;

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        while (true) {
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals(Main.EOF)) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }
        }
    }
}

//Advantage of Synchronization
// We can prevent thread interference

//Disadvantage of Synchronization
// 1. Thread which is blocked waiting to execute the synchronized code can't be interrupted.
// Once they are blocked they are stuck there until they get lock for the object the code is synchronizing on
// 2. We can't start synchronized block in one method and end in another method
// 3. We can't see if objects intrinsic lock is available or find out any other information about that lock
// 4. No order for execution for thread waiting in synchronized

//Alternative to synchronization
//implement class java.util.concurrent.locks.lock interface