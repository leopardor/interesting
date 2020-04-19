package com.shu.leetcode.tree.impl;

import com.shu.leetcode.tree.SolveBinaryTreeProblems;
import com.shu.pojo.TreeNode;

import java.util.Stack;

/**
 * @author arlen
 * @since 2020-04-19 10:03
 */
public class SolveBinaryTreeProblemsImpl implements SolveBinaryTreeProblems {
    @Override
    public int deptOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = deptOfTree(root.left);
        int right = deptOfTree(root.right);

        return left > right ? left + 1 : right + 1;
    }

    @Override
    public TreeNode flatten(TreeNode root) {
        TreeNode pre = new TreeNode();
        doFlatten(root, pre);
        return pre;
    }

    private void doFlatten(TreeNode root, TreeNode pre) {
        if (root == null) {
            return;
        }

        doFlatten(root.left, pre);
        doFlatten(root.right, pre);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    @Override
    public void flattenNoneRecursive(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.add(node.right);
            }

            if (node.left != null) {
                stack.add(node.left);
            }

            if (!stack.isEmpty()) {
                node.right = stack.peek();
            }
            node.left = null;
        }
    }

    @Override
    public TreeNode lastParentNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lastParentNode(root.left, p, q);
        TreeNode right = lastParentNode(root.right, p, q);

        // 可以理解为只要当前节点符合寻找要求 left和right一定不为空 也就是当前状态是符合要求的
        // 也就是说 只要当前节点搜索的子树 左右结果都存在 说明当前就是最近公共父节点
        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }
}
