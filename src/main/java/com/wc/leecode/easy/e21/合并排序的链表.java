package com.wc.leecode.easy.e21;

/**
 * Definition for singly-linked list.
 */


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        merge(l1,l2,node);
        return node.next;
    }

    public void merge(ListNode a, ListNode b, ListNode to) {
        if (a == null && b == null) return ;
        if( a == null || b == null ){
            if(a != null){
                to.next = a;
            } else to.next = b;
            return;
        }
        if (a.val > b.val) {
            to.next = new ListNode(b.val);
            b = b.next;
        }else if (a.val < b.val) {
            to.next = new ListNode(a.val);
            a = a.next;
        }else{
            to.next = new ListNode(a.val);
            to = to.next;
            to.next = new ListNode(a.val);
            a = a.next;
            b = b.next;

        }
        merge(a,b,to.next);
        return ;

    }


}
//
//public class 合并排序的链表 {
////    public static void main (String[] args){
////        ListNode = new
////    }
//}
