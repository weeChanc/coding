package com.wc;


import com.wc.ds.heap.ArrayHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by осёз on 2018/4/23.
 */
public class Main {
    public static void main(String[] args) {
        ArrayHeap<Integer> heap = new ArrayHeap<>(ArrayHeap.TYPE.MIN_HEAP);
        heap.build(4,6,8,5,9);
        heap.insert(123);
        heap.insert(1);
        while (true){
            Integer d = heap.delete();
            if(d == null) break;
            System.out.println(d);
        }
    }
}


