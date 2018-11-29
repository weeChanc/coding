package com.wc.interview;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class CollectionStudy {
    public static void main(String[] args) throws InterruptedException {
//        int[] A = {9,8,7};
//        int[] B = {0, 0,0,0,0,0,0,0,0,0,0};
//        System.arraycopy(A,1,B,2,A.length-1);
////      B = [ 0,0,8,7,0,0,0,0,0,0]
////
////        Arrays.copyOf()
//
//
//        Object[] a = new Object[Integer.MAX_VALUE >> 3];Object[ ] b = new Object[Integer.MAX_VALUE >> 10];
//        Thread.sleep(100000);

//        //直接内存 不由jvm所限制
//
//        ByteBuffer bf = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
//        ByteBuffer b2f = ByteBuffer.allocateDirect(1024 * 1024 * 1024 / 2);
//        Thread.sleep(100000);

        Integer[] result = (Integer[]) Array.newInstance(Integer.class,100);
        String[] abb = new String[]{"1","2","3"};
        Class a = int[].class;
        Arrays.copyOf(result,2,Integer[].class);

//        ArrayList::add/
    }
}
