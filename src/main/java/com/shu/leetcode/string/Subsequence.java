package com.shu.leetcode.string;

public class Subsequence {

    // 最大连续子序列和问题
    public static int getMaxSubSqu(int[] nums) {

        int maxSum = nums[0];
        int maxWhere = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            if (maxWhere <= 0) {
                maxWhere = nums[i];
            } else {
                maxWhere += nums[i];
            }

            if (maxWhere > maxSum) {
                maxSum = maxWhere;
            }
        }

        return maxSum;
    }

    // 子序列问题 s为小串 t为大串
    public static boolean isContain(String s, String t) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            // 当两者相等的时候 指向小串的指针 移动一次
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }

    // 最大连续子序列和
    public int maxSubsequence(int[] nums) {

        int maxSum = nums[0];
        int numHere = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            if (numHere < 0) {
                numHere = nums[i];
            } else {
                numHere += nums[i];
            }

            if (numHere > maxSum) {
                maxSum = numHere;
            }
        }

        return maxSum;
    }
}
