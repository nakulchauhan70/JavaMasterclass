package com.masterclass.cloning.deepcopy;

import java.util.Arrays;

public class DeepCopy {
    int[] arr;

    public DeepCopy(int[] arr) {
        this.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }

    @Override
    public String toString() {
        return "ShallowCopy{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}

class DeepCopyDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        DeepCopy deepCopy = new DeepCopy(arr);

        System.out.println(deepCopy);

        arr[0] = 99;

        System.out.println(deepCopy);
    }
}