package com.shu.leetcode.list;

import com.shu.leetcode.list.ListNode;

public class KListNode {


    // 链表反转
    public ListNode reverse(ListNode head) {

        // 三指针 思路很清晰 pre始终指向头， cur指向当前，nex指向下一个
        ListNode pre = null;
        ListNode cur = head;
        ListNode nex = head;
        while (cur != null) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }

        return pre;
    }

    // 反转区间[a,  b)链表
    public ListNode reverse(ListNode a, ListNode b) {

        ListNode pre = null;
        ListNode cur = a;
        ListNode nex = a;

        while (cur != b) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }

        return pre;
    }

    // k组链表反转
    public ListNode reverseGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; ++i) {
            // 不足k个 不需要反转
            if (b == null) {
                return head;
            }
            b = b.next;
        }

        // 反转前k个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseGroup(b, k);

        return newHead;
    }
}
