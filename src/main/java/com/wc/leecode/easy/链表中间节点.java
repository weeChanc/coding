package com.wc.leecode.easy;

import java.util.ArrayList;
import java.util.List;


public class 链表中间节点 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;


        ListNode nn = middleNode(node1);
        System.out.print(123);
    }

    public static ListNode middleNode (ListNode head){
        ListNode myHead = head;
        List<ListNode> nodes = new ArrayList<>();
        if (myHead != null) nodes.add(myHead);
        if (myHead.next != null) {
            nodes.add(myHead.next);
            myHead = myHead.next;
        }
        int middle;
        if (nodes.size() % 2 == 0) {
            middle = nodes.size() / 2;
        } else {
            middle = nodes.size() / 2 + 1;
        }
        return nodes.get(middle);
    }
}
