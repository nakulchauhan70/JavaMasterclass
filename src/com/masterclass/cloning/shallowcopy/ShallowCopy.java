package com.masterclass.cloning.shallowcopy;

import java.util.Arrays;

public class ShallowCopy {
    int[] arr;

    public ShallowCopy(int[] arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "ShallowCopy{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
}

class ShallowCopyDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ShallowCopy shallowCopy = new ShallowCopy(arr);

        System.out.println(shallowCopy);

        arr[0] = 99;

        System.out.println(shallowCopy);
    }
}