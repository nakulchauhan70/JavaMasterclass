package com.masterclass;

import java.lang.reflect.Constructor;

public class ThreadSafeSingletonClassMain {
    public static void main(String[] args) {
        //Ways to break singleton
        //Reflection
        ThreadSafeSingletonClass obj1 = ThreadSafeSingletonClass.getInstance();
        try {
            Constructor<ThreadSafeSingletonClass> constructor = ThreadSafeSingletonClass.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            ThreadSafeSingletonClass obj2 = constructor.newInstance();
            System.out.println(obj1.hashCode());
            System.out.println(obj2.hashCode());
        } catch (Exception e) {

        }
    }
}


final class ThreadSafeSingletonClass {
    private static ThreadSafeSingletonClass instance;

    private ThreadSafeSingletonClass() {
    }

    public static ThreadSafeSingletonClass getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingletonClass.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingletonClass();
                }
            }
        }

        return instance;
    }
}