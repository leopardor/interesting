package com.shu.leetcode.array;

import java.util.*;

public class TopK {

    private static ArrayList<Integer> topK(int[] nums, int k) {
        if (k < 1 || k > nums.length) {
            return new ArrayList<>();
        }

        Queue<Integer> queue = new PriorityQueue<>();

        for (int num : nums) {
            if (queue.size() < k) {
                queue.add(num);
            } else if (queue.peek() < num) {
                // 剔除最小的 并放入较大的
                queue.poll();
                queue.add(num);
            }
        }

        return new ArrayList<>(queue);
    }


    public static ArrayList<Integer> maxInWindows(int [] num, int size) {
        if (num == null || num.length < size) {
            return new ArrayList<>();
        }

        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < num.length; ++i) {
            if (i < size) {
                queue.add(num[i]);
            } else {
                res.add(queue.peek());
                queue.remove(num[i - size]);
                queue.add(num[i]);
            }
        }
        res.add(queue.peek());

        return res;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2, 3, 1, 6, -5, 9, 10};
//        System.out.println(topK(nums, 5));

        maxInWindows(new int[]{2,3,4,2,6,2,5,1}, 3);
        double pow = Math.pow(3, 4);
        Double.valueOf(pow).intValue();
//        pow.intValue()

        int[] numss = new int[]{1, 3, 4, 5, 6, 7};
        int[] res = Arrays.copyOf(numss, 4);
        System.out.println(Arrays.toString(res));
        Arrays.asList(1, 2, 3);
        List<List<Integer>> resList = new ArrayList<List<Integer>>();
        resList.add(Arrays.asList(1, 2, 3));
    }


}
