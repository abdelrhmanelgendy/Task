package com.tasks.navigationcomponent;

import java.util.*;

public class Mainlly {
    public static void main(String[] args) {
        int solution = new Solution().solution(new int[]{2, 6, 8, 5});
        System.out.println(solution);
    }
}

class Solution {

    static int solution(int blocks[]) {
        int n = blocks.length;
        int maxDistance = 0;

        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i;
            int maxHeight = blocks[i];

            while (left > 0 && blocks[left - 1] >= maxHeight) {
                left--;
                maxHeight = blocks[left];
            }

            while (right < n - 1 && blocks[right + 1] >= maxHeight) {
                right++;
                maxHeight = blocks[right];
            }

            maxDistance = Math.max(maxDistance, right - left + 1);
        }

        return maxDistance;
    }
}



