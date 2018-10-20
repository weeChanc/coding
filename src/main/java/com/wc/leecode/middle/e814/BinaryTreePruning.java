package com.wc.leecode.middle.e814;

import javax.swing.tree.TreeNode;

/**
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 *
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 *
 * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
 *
 * Example 1:
 * Input: [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 *
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 *
 *
 * Example 2:
 * Input: [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 *
 * 修减掉没有包含1的子树
 */




public class BinaryTreePruning {
    public static void main(String[] args){
        Solution.TreeNode root  = new Solution.TreeNode(1);
        root.left = null;
        Solution.TreeNode right = new Solution.TreeNode(0);
        root.right = right;
        right.left = new Solution.TreeNode(0);
        right.right = new Solution.TreeNode(1);
        Solution.TreeNode node = new Solution().pruneTree(root);
        System.out.println(node);
    }
}


class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return root.val == 1 || root.left != null || root.right != null? root:null;

    }
}
