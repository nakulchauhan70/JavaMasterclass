package com.masterclass;

import java.util.*;

public class Main1 {

    public static void main(String[] args) {
////        ATecnotree1!
////                BTecnotree2@
////                CTecnotree3#
////        DTecnotree4$
////        ETecnotree5%
////                system-k
//        String originalInput = "ETecnotree5%";
////        for (int i = 0; i < 10; i++) {
//        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//        System.out.println(encodedString);
//        System.out.println(new String(Base64.getDecoder().decode(encodedString)));

//    }

        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

class Message {
    private String system;
    private String message;
    private boolean empty = true;

    public String getSystem() {
        return system;
    }

    public String getMessage() {
        return message;
    }

    public boolean isEmpty() {
        return empty;
    }

    public synchronized Message read() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = true;
        notifyAll();
        return this;
    }

    public synchronized void write(String message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Writer implements Runnable {
    private final Message message;
    Scanner sc = new Scanner(System.in);

    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        int testCases = 6;
        int count = 0;
        String[] messages = new String[testCases];
        char[] ch = new char[]{'a', 'b', 'c', 'd', 'e', '0'};
        while (count < testCases) {
            String s = sc.nextLine();
            if (s.contains("system")) {
                messages[count] = "system-" + ch[count] + "_" + s;
            } else {
                messages[count] = "system-" + ch[count] + "_" + s + "_" + getEncodedPassword(s);
            }
            count++;
        }
        System.out.println(Arrays.toString(messages));
        for (int i = 0; i < messages.length; i++) {
            String[] s = messages[i].split("_");
            if (s.length == 2) {
                Optional<String> first = Arrays.stream(messages).filter(m -> m.equalsIgnoreCase(s[0])).map(m -> m.split("_")[1]).findFirst();
                if (first.isPresent()) {
                    message.write(s[0] + "_" + first.get());
                } else {
                    message.write(s[0]);
                }
            } else {
                message.write(messages[i]);
            }
        }
        message.write("Finished");
    }

    private String getEncodedPassword(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }
}

class Reader implements Runnable {
    private final Message message;

    public Reader(Message message) {
        this.message = message;
    }

    public void run() {
        List<String> allowedSystems = List.of("system-a", "system-b", "system-c", "system-d", "system-e");
        for (Message latestMessage = message.read(); !latestMessage.getMessage().equalsIgnoreCase("Finished"); latestMessage = message.read()) {
            String[] m = latestMessage.getMessage().split("_");
            if (m.length == 2) {
                System.out.println("Reading Security Password for " + m[0] + ": " + m[1]);
            } else if (m.length == 1) {
                System.out.println("Entered system is not available....." + m[0]);
            } else {
                System.out.println("Encoded password for " + m[0] + ": " + m[2]);
            }
        }
    }

    private String getDecodedPassword(String s) {
        return new String(Base64.getDecoder().decode(s));
    }
}






