package com.shu.leetcode.array;

import java.util.*;

public class ThreeSum {

    // twoSum 问题
    public static int[] towSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(target - nums[i]), i};
            }
        }

        return new int[]{-1, -1};
    }


    public static List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 1; ++i) {
            // skip same result
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            int target = -nums[i];
            while (j < k) {
                int temp = nums[j] + nums[k];
                if (temp == target) {
                    resList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 去重
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (temp < target) {
                    j++;
                } else if (temp > target) {
                    k--;
                }
            }
        }

        return resList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> resList = threeSum(nums);

        for (List list: resList) {
            System.out.println(Collections.singletonList(list).toString());
        }

//        int[] nums = new int[]{2,7,11,15};
//        towSum(nums, 9);

    }


}
