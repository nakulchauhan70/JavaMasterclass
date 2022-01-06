package com.masterclass.concurrency.synchronization.conditionvariable; /**
 * Two hungry people, anxiously waiting for their turn to take soup
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HungryPersonDetails extends Thread {

    private static Lock slowCookerLid = new ReentrantLock();
    private static int servings = 11;
    private static Condition soupTaken = slowCookerLid.newCondition();
    private int personID;

    public HungryPersonDetails(int personID) {
        this.personID = personID;
    }

    public void run() {
        while (servings > 0) {
            slowCookerLid.lock();
            try {
                while ((personID != servings % 5) && servings > 0) { // check if it's not your turn
                    System.out.format("Person %d checked... then put the lid back.\n", personID);
                    soupTaken.await();
                }
                if (servings > 0) {
                    servings--; // it's your turn - take some soup!
                    System.out.format("Person %d took some soup! Servings left: %d\n", personID, servings);
                    soupTaken.signalAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                slowCookerLid.unlock();
            }
        }
    }
}

public class ConditionVariableSolution {
    public static void main(String args[]) {
        for (int i = 0; i < 5; i++)
            new HungryPersonDetails(i).start();
    }
}