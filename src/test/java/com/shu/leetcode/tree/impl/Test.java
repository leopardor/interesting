package com.shu.leetcode.tree.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author arlen
 * @since 2020-06-06 15:36
 */
public class Test {

    public static void main(String[] args) {

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.poll();

        List<Integer> list = new ArrayList<>();
        list.add(1);

        int[] arrays = {1, 2, 3, 4, 5};
        Test test = new Test();
        test.printArray(arrays);
    }

    private void printArray(int[] arrays) {
        doPrintArray(arrays, 0);
    }

    private void doPrintArray(int[] arrays, int index) {
        if (index < arrays.length) {
            System.out.println(arrays[index]);
            doPrintArray(arrays, index + 1);
        }
    }
}
