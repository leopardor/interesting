package com.shu.leetcode.array;

import java.util.concurrent.atomic.AtomicInteger;


public class BinarySearch {

    // 基本的二分查找
    public static int binarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        // 这里能取到等号 是因为 可能left 取到值 nums.length - 1;如果不取等号 如果要找的值是最后一个值 是找不到的
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (target == nums[middle]) {
                return middle;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            }
        }

        return -1;
    }

    // 寻找左侧边界的二分查找
    public static int binarySearch2(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (target == nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            }
        }

        // 这两种情况就是没找到target的情况
        // 这里面搜索左侧边界，如果left已经大于等于nums.length时，说明肯定不存在
        // 还有就是right可能走到-1 但是此时可以直接判断nums[left], 能走到这一步说明要找到数太小了 根本就没有在查找范围内
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }

        // 寻找那边边界 就找判断那边 找左侧就判断 left >= nums.length && nums[left] != target 找右侧就判断 right < 0 && nums[right] != target

        return left;
    }

    // 二分查找 寻找右侧边界
    private static int binarySearch3(int[] nums, int target) {
        if (nums.length < 1) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] == target) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            }
        }

        // 这里left一直右移，但是left始终是小于right
        if (right < 0 || nums[right] != target) {
            return -1;
        }

        return right;
    }

    private static int binarySearch6(int[] nums, int target){
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (right - left) / 2 + left;
            if (nums[middle] == target) {
                right = middle - 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            }
        }

        if (left == nums.length || nums[left] != target) {
            return -1;
        }

        return left;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(binarySearch2(nums, 5));
        System.out.println(binarySearch3(nums, 5));


        // 凄惨的人生 我该如何面对 真的难受至极
    }
}
