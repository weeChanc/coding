package com.wc.leecode.easy;

public class 一bits的数量 {
    static class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            long nn = n & 4294967295L;
            int count = 0 ;
            while(nn > 0){
                nn =( nn - 1 ) & nn;
                count ++;
            }
            return count;
        }
    }

    public static void main (String[] args){
        System.out.println(new Solution().hammingWeight(Integer.MAX_VALUE + 3));
    }
} 
