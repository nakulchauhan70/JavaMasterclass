package com.masterclass.package_final_static._finalstatic.exercise1;

public class SIBTest {
    public static final String owner;

    static {
        owner = "tim";
        System.out.println("SIBTest static initialization block called");
    }

    static {
        System.out.println("2nd initialization block called");
    }

    public SIBTest() {
        System.out.println("SIB constructor called");
    }

    public void someMethod() {
        System.out.println("someMethod called");
    }
}
