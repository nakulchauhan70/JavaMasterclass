package com.masterclass.concurrency.challenge7;

public class Tutor {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void studyTime() {
        synchronized (this) {
            System.out.println("Tutor has arrived");
            synchronized (student) {
                try {
                    // wait for student to arrive
                    this.wait();
                } catch (InterruptedException e) {

                }
                student.startStudy();
                System.out.println("Tutor is studying with student");
            }
        }
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report");
    }
}