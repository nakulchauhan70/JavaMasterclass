package com.masterclass.concurrency.challenge7;

public class Student {

    private Tutor tutor;

    Student(Tutor tutor) {
        this.tutor = tutor;
    }

    public void startStudy() {
        // study
        System.out.println("Student is studying");
    }

    public void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment");
                tutor.notifyAll();
            }
        }
    }
}