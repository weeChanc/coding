package com.wc.leecode.easy;

import java.util.List;


public class 树的最深深度559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    class Solution {
        public int maxDepth(Node root) {
            int maxDepth = 1 ;
            if(root == null) return 0;
            if(root.children == null) return maxDepth;

            for(Node child : root.children){
                int childDepth = maxDepth(child) + 1;
                if(childDepth > maxDepth){
                    maxDepth = childDepth;
                }
            }
            return maxDepth;
        }
    }
}
