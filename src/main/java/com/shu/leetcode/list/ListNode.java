package com.shu.leetcode.list;

public class ListNode {

    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode() {
    }


    // 两个链表相加
    public static ListNode addTwoList(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }

        if (p2 == null) {
            return p1;
        }

        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (p1 != null || p2 != null || carry != 0) {
            int val1 = p1 == null ? 0 : p1.val;
            int val2 = p2 == null ? 0 : p2.val;

            int sum = val1 + val2 + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = node;

            p1 = p1 == null ? p1 : p1.next;
            p2 = p2 == null ? p2 : p2.next;
        }

        return pre.next;
    }

    // 两个有序链表合成一个有序链表
    public static ListNode mergeList(ListNode p1, ListNode p2) {

        ListNode head = new ListNode(0);
        ListNode p = head;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return head.next;
    }


    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);

        p1.next = p2;
        p2.next = p3;

        ListNode p4 = new ListNode(7);
        ListNode p5 = new ListNode(8);
//        ListNode p6 = new ListNode(9);
        p4.next = p5;
//        p5.next = p6;

        // 321 + 987 = 1308 链表存储就是 8 0 3 1
        ListNode newHead = addTwoList(p1, p4);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }

        System.out.println(2 + 0.0);
    }
}
