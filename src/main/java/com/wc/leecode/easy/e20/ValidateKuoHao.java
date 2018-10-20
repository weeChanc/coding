package com.wc.leecode.easy.e14.e20;

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        char[] arrays = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : arrays) {
            if(stack.isEmpty() || !match(stack.peek(),c)){
                stack.push(c);
            }else{
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    private boolean match(char a,char b){
        return  a == '[' && b == ']' || a == '(' && b == ')' || a == '{' && b == '}';
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}

public class ValidateKuoHao {
    public static void main (String[] args){
    }
}
