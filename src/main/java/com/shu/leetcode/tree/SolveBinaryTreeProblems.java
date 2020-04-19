package com.shu.leetcode.tree;

import com.shu.pojo.TreeNode;

/**
 * Basic questions about binary tree
 * @author arlen
 * @since 2020-04-18 15:12
 */
public interface SolveBinaryTreeProblems {

    /**
     * 二叉树的最大深度
     * @param root 根节点
     * @return
     */
    int deptOfTree(TreeNode root);

    /**
     * 二叉树转链表递归实现
     * @param root 根节点
     */
    TreeNode flatten(TreeNode root);

    /**
     * 二叉树转链表非递归实现
     * @param root 根节点
     * @return
     */
    void flattenNoneRecursive(TreeNode root);


    /**
     * 二叉树的最近公共父节点
     * @param root 根节点
     * @param p 节点
     * @param q 节点
     * @return 最近公共父节点
     */
    TreeNode lastParentNode(TreeNode root, TreeNode p, TreeNode q);
}
