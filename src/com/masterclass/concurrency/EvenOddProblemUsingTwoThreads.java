package com.masterclass.concurrency;

public class EvenOddProblemUsingTwoThreads {
    static int N = 10;
    // Starting counter
    int counter = 1;

    public static void main(String[] args) {
        EvenOddProblemUsingTwoThreads mt = new EvenOddProblemUsingTwoThreads();
        new Thread(mt::printEvenNumber).start();

        new Thread(mt::printOddNumber).start();
    }

    public void printOddNumber() {
        synchronized (this) {
            while (counter < N) {

                // If count is even then print
                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Print the number
                System.out.print(counter + " ");

                // Increment counter
                counter++;

                // Notify to second thread
                notify();
            }
        }
    }

    public void printEvenNumber() {
        synchronized (this) {
            while (counter < N) {

                // If count is odd then print
                while (counter % 2 == 1) {
                    try {
                        wait();
                    } catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Print the number
                System.out.print(counter + " ");

                // Increment counter
                counter++;

                // Notify to 2nd thread
                notify();
            }
        }
    }
}

//If we remove any synchronized block we will get IllegalMonitorStateException - we'll get this exception if we call one of the wait(), notify(), or notifyAll() methods of the Object class outside of a synchronized block