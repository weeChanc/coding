package com.wc.leecode.middle;


/**
 *  考虑要周到,相加超出表示范围,
 *  最后一个进位也要加入等等情况
 */
public class 链表相加 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode root = new ListNode(-1);
            ListNode r = root;
            while (l1 != null || l2 != null) {
                int tt = add(l1, l2);
                root.next = new ListNode(tt);
                root = root.next;
                l1 = l1 == null ? null : l1.next;
                l2 = l2 == null ? null : l2.next;
            }
            return r.next;
        }

        private int add(ListNode a, ListNode b) {
            int result;
            if (a != null && b != null)
                result = a.val + b.val;
            else {
                if (a != null) return a.val;
                return b.val;
            }

            int overflow = result % 10;
            if (result != overflow) {
                if (a.next == null) {
                    a.next = new ListNode(1);
                } else if (b.next == null) {
                    b.next = new ListNode(1);
                } else {
                    a.next.val++;
                }
            }
            return overflow;

        }
    }

}
