package com.masterclass.oop;

public class Car {
    private int age;

    public class Inner {
        int age = 10;

        public void f() {
            for (int i = 0; i < 5; i++) {
                System.out.println(i * this.age);
            }
        }
    }
}


//new Car().new Inner().f()