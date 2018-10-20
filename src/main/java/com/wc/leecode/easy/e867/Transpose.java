package com.wc.leecode.easy.e867;

public class Transpose {
    public static int[][] transpose(int[][] A) {
        int[][] B = new int[A[0].length][A.length];
        for(int i = 0 ; i < A.length ; i++){
            for(int j = i ; j < A[i].length ; j ++){
                int a = A[i][j];
                B[j][i] = a;

            }
        }
        return A;
    }
    public static void main(String[] args){
        transpose(new int[][]{{1,2,3},{4,5,6}});
    }
}
