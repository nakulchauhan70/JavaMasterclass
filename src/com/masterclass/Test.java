package com.masterclass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class E {
    String s1;

    public void d(E s) {
        this.s1 = "DS";
        s = null;
    }
}

public class Test {
    public static void main(String[] args) {
        Test t = new Test();
        int i = t.check();
        System.out.println(i);
        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(20);
        Iterator<Integer> iterator = l.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            l.add(5);
        }
        E e = new E();
        e.s1 = "sdfdsds";
        e.d(e);
        System.out.println(e.s1);
//        while (true) {
//            try {
//                if (true) {
//                    System.out.println("in continue");
//                    continue;
//                }
//                System.out.println("after continue");
//            } finally {
//                System.out.println("in finally");
//            }
//        }

//        System.out.println(solution(new int[]{1, -2, -3, 5}));//1
//        System.out.println(solution(new int[]{1, 2, 3, -5}));//-1
//        System.out.println(solution(new int[]{1, 2, 0, 5})); //0
//        System.out.println(solution(new int[]{0, 0, 0, 0})); //0

    }

    public static int solution(int[] A) {
        int negativeCount = 0;
        for (int i : A) {
            if (i == 0) {
                return 0;
            } else if (i < 0) {
                negativeCount++;
            }
        }

        return negativeCount % 2 == 0 ? 1 : -1;
//        int prod = Arrays.stream(A).reduce(1, (n1, n2) -> n1 * n2);
//
//        if (prod == 0) {
//            return 0;
//        } else if (prod > 1) {
//            return 1;
//        } else {
//            return -1;
//        }
    }

    private int check() {

        try {
            System.out.println("try");
            return 1;
        } catch (Exception e) {
            System.out.println("catch block");
            return 2;
        } finally {
            System.out.println("finally");
            return 3;
        }
    }
}
