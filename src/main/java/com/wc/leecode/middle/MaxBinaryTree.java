package com.wc.leecode.middle;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 */
public class MaxBinaryTree {
    public static void main(String []args){
        TreeNode node = new Solution().constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});
        System.out.println(node);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = getMaxNode(nums,0,nums.length-1);
        return root;
    }

    private TreeNode getMaxNode(int[] nums, int from, int to){
        if(from >= nums.length ) return null;
        if(to < 0) return null;
        if(from > to ) return null;
        int[] result = maxRange(nums,from,to);
        int value = result[0];
        int index = result[1];
        TreeNode node = new TreeNode(value);
        node.left = getMaxNode(nums,from,index - 1 );
        node.right = getMaxNode(nums,index + 1,to);
        return node;
    }

    private int[] maxRange(int[] nums , int from , int to){
        int max = nums[from];
        int index = from ;
        for(int i = from + 1 ; i <= to ; i++ ){
            if(nums[i] > max){
                max = nums[i];
                index = i ;
            }
        }
        return new int[]{max,index};
    }
}