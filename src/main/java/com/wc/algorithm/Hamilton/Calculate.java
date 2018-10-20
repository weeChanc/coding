package com.wc.algorithm.Hamilton;


public class Calculate {

    static int[][] graphMatrix = new int[15][15];
    static int[] nodes = new int[15];

    public static void main(String[] args) {
        graphMatrix[0][1] = 1;
        graphMatrix[1][0] = 1;
        graphMatrix[0][3] = 1;
        graphMatrix[3][0] = 1;
        graphMatrix[3][1] = 1;
        graphMatrix[1][3] = 1;
        graphMatrix[1][4] = 1;
        graphMatrix[4][1] = 1;
        graphMatrix[5][4] = 1;
        graphMatrix[4][5] = 1;
        dfs(0);
    }

    private static  void dfs(int i) {
        visit(i);
        boolean flag = false;
        for (int matrix = 0 ; matrix <  graphMatrix[i].length ; matrix++) {
            if(graphMatrix[i][matrix] == 1 && nodes[matrix] != 1){
                dfs(matrix);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("wulukezou");
        }
    }

    private static  void visit(int i) {
        if (nodes[i] != 1) {
            nodes[i] = 1;
            System.out.println("visit" + i);
        }
    }

}
