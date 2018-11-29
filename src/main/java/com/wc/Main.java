package com.wc;


import com.sun.jmx.remote.internal.ArrayQueue;
import com.wc.ds.heap.ArrayHeap;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by осёз on 2018/4/23.
 */
public class Main {
    public static void main(String[] args) {

        ListNode l = new ListNode(1);
        l.next = new ListNode(2);

        ListNode r= new ListNode(2);
        new ListNode.Solution().addTwoNumbers(l,r);

    }

}

 class ListNode {
     int val;
     ListNode next;

     ListNode(int x) {
         val = x;
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