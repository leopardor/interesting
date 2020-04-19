package com.shu.leetcode.tree;

import com.shu.pojo.TreeNode;
import com.sun.source.tree.Tree;

import java.util.*;

/**
 * Traverse the tree in different ways
 * @author arlen
 * @since 2020-04-18 11:35
 */
public interface TraverseTree {

    /**
     * 先序遍历
     * @param root 根节点
     */
    void pederTraversal(TreeNode root);

    /**
     * 中序遍历
     * @param root 根节点
     */
    void inOrderTraversal(TreeNode root);

    /**
     * 后序遍历
     * @param root 根节点
     */
    void postOrderTraversal(TreeNode root);


    /**
     * 层序遍历
     * @param root 根节点
     * @return 层序遍历结果
     */
    default List<Integer> sequenceTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> resultList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.peek();
            resultList.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
            queue.poll();
        }

        return resultList;
    }

    /**
     * 层序遍历并返回每层遍历的结果
     * @param root 根节点
     * @return 每层的遍历结果
     */
    default List<List<Integer>> sequenceTraversalWithResult(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> resultList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            List<Integer> temp = new ArrayList<>(curSize);
            int i = 0;
            // 当前队列的大小 就是上一层的节点个数，一次出队.也就是只取当前层的节点 一个窗口一样，在取得同时 将子节点压入队列中，为后续遍历做准备
            // 有点类似于 将二叉树线性化 但是这里是按层次
            while (i < curSize) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                temp.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }

                i++;
            }

            resultList.add(temp);
        }

        return resultList;
    }

    /**
     * 之字形遍历
     * @param root 根节点
     * @return 之字形遍历结果
     */
    default List<List<Integer>> zigzagTraversal(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }

        int layer = 1;

        // 将奇数层和偶数层分开存取
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.push(root);
        Stack<TreeNode> stack2 = new Stack<>();

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            // 奇数层
            if ((layer & 1) == 1) {
                ArrayList<Integer> temp = new ArrayList<>();
                while (!stack1.isEmpty()) {
                    TreeNode node = stack1.pop();
                    temp.add(node.val);
                    if (node.left != null) {
                        stack2.add(node.left);
                    }

                    if (node.right != null) {
                        stack2.add(node.right);
                    }
                }

                resultList.add(temp);
                layer++;
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                while (!stack2.isEmpty()) {
                    TreeNode node = stack2.pop();
                    temp.add(node.val);
                    if (node.right != null) {
                        stack1.add(node.right);
                    }

                    if (node.left != null) {
                        stack1.add(node.left);
                    }
                }

                resultList.add(temp);
                layer++;
            }
        }

        return resultList;
    }
}
