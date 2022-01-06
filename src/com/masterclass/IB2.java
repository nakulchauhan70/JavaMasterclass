package com.masterclass;

public class IB2 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 0, 1, 0, 1, 1})); //1
        System.out.println(solution(new int[]{1, 1, 0, 1, 1})); //2
        System.out.println(solution(new int[]{0, 1, 0})); //0
        System.out.println(solution(new int[]{0, 1, 1, 0})); //2
    }

    public static int solution(int[] A) {
        int headFlipCount = 0;
        int tailFlipCount = 0;

        for (int i = 0; i < A.length; i++) {
            if ((1 - (i % 2)) == A[i]) {
                headFlipCount++;
            }

            if ((i % 2) == A[i]) {
                tailFlipCount++;
            }
        }

        return Math.min(headFlipCount, tailFlipCount);
    }
}
