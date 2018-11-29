package com.wc.leecode.middle;

import java.util.*;

public class 没有重复字符的最大子串 {

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int maxLength = 0;
            List<Character> added = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                removeIfMatch(added, c, 0);

                maxLength = maxLength > added.size() ? maxLength : added.size();
            }
            return maxLength;
        }

        public int lengthOfLongestSubstring2(String s) {
            int maxLength = 0;
            Map<Character, Integer> added = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int gap = 0;
                if (added.containsKey(c)) {
                    gap = added.get(c);
                }
                added.put(c, i);
                maxLength = maxLength > added.size() - gap ? maxLength : added.size() - gap;
            }
            return maxLength;
        }
    }

    private boolean removeIfMatch(List<Character> added, char c, int index) {
        if (added.size() <= index) return false;
        if (added.get(index) == c) {
            added.remove(index);
            return true;
        }
        if (removeIfMatch(added, c, index + 1)) {
            added.remove(index);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
