package com.masterclass;

import java.util.*;

public class FrogJump {
    public static void main(String[] args) {

    }

    public boolean canCross(int[] stones) {
        if (stones[1] != 1) {
            return false;
        }
        ArrayList<Set<Integer>> dp = new ArrayList<Set<Integer>>();
        Map<Integer, Integer> locs = new HashMap();
        for (int i = 0; i < stones.length; i++) {
            dp.add(new HashSet());
            locs.put(stones[i], i);
        }

        dp.get(1).add(1);
        for (int i = 1; i < stones.length; i++) {
            int currPos = stones[i];
            Set<Integer> prevJumps = dp.get(i);
            for (int j : prevJumps) {
                // int idx1 = Arrays.binarySearch(stones, currPos + j);
                // int idx2 = Arrays.binarySearch(stones, currPos + j + 1);
                int idx1 = locs.getOrDefault(currPos + j, -1);
                int idx2 = locs.getOrDefault(currPos + j + 1, -1);

                if (idx1 >= 0) {
                    dp.get(idx1).add(j);
                }
                if (idx2 >= 0) {
                    dp.get(idx2).add(j + 1);
                }

                if (j > 1) {
                    //int idx3 = Arrays.binarySearch(stones, currPos + j - 1);
                    int idx3 = locs.getOrDefault(currPos + j - 1, -1);
                    if (idx3 >= 0) {
                        dp.get(idx3).add(j - 1);
                    }
                }
            }
        }
        return dp.get(stones.length - 1).size() > 0;
    }
}
