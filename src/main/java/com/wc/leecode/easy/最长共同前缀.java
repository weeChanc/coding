package com.wc.leecode.easy;


public class 最长共同前缀 {
    public static void main (String[] args){

    }

    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if(strs.length == 0 ) return  "";
            StringBuffer sb = new StringBuffer();
            char[] firstCharArray = strs[0].toCharArray();
            for (int i = 0 ; i < firstCharArray.length ; i++) {
                char a = firstCharArray[i];
                for (String str : strs) {
                    if(str.charAt(i) != a) return sb.toString();
                }
                sb.append(a);
            }

            return sb.toString();
        }
    }
}
