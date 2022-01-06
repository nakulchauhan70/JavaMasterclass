package com.masterclass;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        Employee e = new Employee();
        e.name = "jjjb";
        e.id = 1;

        HashMap<String, Employee> map = new HashMap<>();
        map.put("Nakul", e);

        System.out.println(map.get("Nakul"));

        e.name = "JJBHBHHB";
        System.out.println(map.get("Nakul"));
        System.out.println("=========");
        List<String> l = new ArrayList<>();
        System.out.println(Thread.currentThread());
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        queue.add(stack.pop());

        stack.push("D");
        stack.push("E");

        queue.add("F");

        stack.push(queue.remove());

        queue.add(stack.pop());
        queue.add(stack.pop());

        System.out.println(stack);
        System.out.println(queue);
    }


    //  - O E
    //4 - 0 + 2
    //6 - 2 + 3
    //8 - 2 + 4
    //10- 2O + 2E +
    public static int noOfWays(int n) {
        n = n * 2;

        if (n % 2 == 0) {
            return n / 2;
        } else {
            return 0;
        }
    }
}

class Employee {
    String name;
    int id;
}