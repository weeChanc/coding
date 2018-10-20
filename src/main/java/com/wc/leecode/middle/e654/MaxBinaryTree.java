package com.wc.leecode.middle.e654;

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