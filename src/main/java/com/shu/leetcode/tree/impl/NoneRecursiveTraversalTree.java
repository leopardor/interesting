package com.shu.leetcode.tree.impl;

import com.shu.leetcode.tree.TraverseTree;
import com.shu.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Traversal tree use none recursive
 * @author arlen
 * @since 2020-04-18 11:58
 */
public class NoneRecursiveTraversalTree implements TraverseTree {

    private final static ThreadLocal<List<Integer>> listThreadLocal = new ThreadLocal<>();

    @Override
    public void pederTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                resultList.add(cur.val);
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            // 转向
            cur = cur.right;
        }
        listThreadLocal.set(resultList);
    }

    @Override
    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            resultList.add(cur.val);
            cur = cur.right;
        }
        listThreadLocal.set(resultList);
    }

    @Override
    public void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            // 当前为根节点或者right已经被访问过
            if (cur.right == null || cur.right == last) {
                resultList.add(cur.val);
                stack.pop();
                last = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        listThreadLocal.set(resultList);
    }

    public List<Integer> printTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode cur = root;
        List<Integer> resultList = new ArrayList<>();
        while (!stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            resultList.add(cur.val);
            cur = cur.right;
        }

        return resultList;
    }
}
