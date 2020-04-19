package com.shu.leetcode.tree.impl;

import com.shu.leetcode.tree.TraverseTree;
import com.shu.pojo.TreeNode;

/**
 * Traversal tree use recursive way
 * @author arlen
 * @since 2020-04-18 11:47
 */
public class RecursiveTraversalTree implements TraverseTree {
    @Override
    public void pederTraversal(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            pederTraversal(root.left);
            pederTraversal(root.right);
        }
    }

    @Override
    public void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.val);
            inOrderTraversal(root.right);
        }
    }

    @Override
    public void postOrderTraversal(TreeNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.val);
        }
    }
}
