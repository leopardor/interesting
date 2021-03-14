package com.shu.leetcode.sort;



import java.util.Arrays;

/**
 * 快速排序
 * 基本思路：
 * 1. 找一个合适的值
 * 2. 基于上面的值使用双指针进行比较 较小的放左边 较大的放右边
 * 3. 递归，分解子任务，的出结果
 */
public class QuickSort {


    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    // 这里直接拿最左边的值 作为比较值
    public static int partition(int[] nums, int left, int right) {
        // 采用三数中值分割的方式选取基准值 避免O N方的时间复杂度
        int mid = (right - left) / 2 + left;
        if (nums[left] > nums[right]) {
            swap(nums, left, right);
        }

        if (nums[mid] > nums[right]) {
            swap(nums, mid, right);
        }

        if (nums[mid] > nums[left]) {
            swap(nums, mid, left);
        }

        int temp = nums[left];

        while (left < right) {

            // 开始时从右向左
            while (nums[right] >= temp && left < right) {
                --right;
            }

            if (left < right) {
                nums[left] = nums[right]; // 比temp小的一定在左边
                left++;
            }

            while (nums[left] <= temp && left < right) {
                ++left;
            }

            if (left < right) {
                nums[right] = nums[left];
                --right;
            }
        }

        nums[left] = temp;
        return left;
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (nums == null || nums.length < 2 || left >= right) {
            return;
        }

        int mid = partition(nums, left, right);
        quickSort(nums, left, mid);
        quickSort(nums, mid + 1, right);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 1, 6, 7, 0, 9, 4};

        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

}
