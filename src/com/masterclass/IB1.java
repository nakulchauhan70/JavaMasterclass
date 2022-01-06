package com.masterclass;

import java.util.Arrays;

public class IB1 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 2, -2, 5, -3}));//3
        System.out.println(solution(new int[]{1, 1, 2 - 1, 2, -1})); //1
        System.out.println(solution(new int[]{1, 2, 3, -4})); //0
    }

    public static int solution(int[] A) {
        Arrays.sort(A);

        int startIndex = 0;
        int endIndex = A.length - 1;
        int t1;
        int t2;

        while (startIndex < endIndex) {
            t1 = Math.abs(A[startIndex]);
            t2 = Math.abs(A[endIndex]);

            if (A[startIndex] + A[endIndex] == 0) {
                return t1;
            } else if (t2 > t1) {
                endIndex--;
            } else if (t1 > t2) {
                startIndex++;
            }
        }

        return 0;
    }
}
