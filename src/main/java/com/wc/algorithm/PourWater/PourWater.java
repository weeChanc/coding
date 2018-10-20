package com.wc.algorithm.PourWater;


import java.util.HashSet;
import java.util.Set;

/**
 * 问题描述:
 * 有一个充满水的8品脱的水壶和两个空水壶（容积分别是5品脱和3品脱）。
 * 通过将水壶完全倒满水和将水壶的水完全倒空这两种方式，
 * 在其中的一个水壶中得到4品脱的水。
 */
public class PourWater {

    public static Set<String> status = new HashSet<>();
    // 8 - 5 - 3
    // 8 - 0 - 0
    public static int[] water = {8,0,0};

    public static void main(String[] args){
        dfs(water);
    }

    public static boolean dfs(int[] water){
        status.add(waterString(water));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 ; j ++) {
                if(i != j){
                    int[] pourResult = pour(i,j,water);
                    if(pourResult != null){
                        water = pourResult;
                        printWater(water);
                        if(checkWater(water)){
                            return true;
                        }
                        if(dfs(water)){
                            return true;
                        };
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param from
     * @param to
     * @return SUCCESS water FAILED -1
     */
    public static int[] pour(int from , int to , int[] water){
        water = water.clone();
        int fromWater = water[from];
        int toWater = water[to];
        int toMax = getWaterMaxContain(to);

        if (toWater == toMax || fromWater == 0 ){
            return null;
        }

        int waterTranfer = toMax-toWater;
        if(waterTranfer > fromWater){
            waterTranfer = fromWater;
        }

        fromWater = fromWater - waterTranfer;
        toWater = toWater + waterTranfer;
        water[from] = fromWater;
        water[to] = toWater;
        if(status.contains(waterString(water))){
            return null;
        }
        return water;
    }

    public static boolean checkWater(int[] water){
        for (int i = 0; i < water.length; i++) {
            if(water[i] == 4){
                return true;
            }
        }
        return false;
    }

    private static int getWaterMaxContain(final int pos){
        switch (pos){
            case 0: return 8;
            case 1: return 5;
            case 2: return 3;
        }
        return -1;
    }

    private static  void  printWater(int[] w){
        System.out.println(""+w[0]+""+w[1]+""+w[2]);
    }

    private static String waterString(int[] w){
        return (""+w[0]+""+w[1]+""+w[2]);
    }



}
