package com.shu.pojo;

public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public int val;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(TreeNode left, TreeNode right, int val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    public static TreeNode defaultTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;

        return root;
    }



    private static int deptTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = deptTree(root.left);
        if (left == -1) {
            return -1;
        }

        int right = deptTree(root.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode node = defaultTree();
        System.out.println(deptTree(node) != -1);
    }

    private static TreeNode getTree() {
        TreeNode head = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        head.left = node1;
        head.right = node2;

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;


//        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(6);

//        node2.left = node5;
        node2.right = node6;

        return head;
    }

}
