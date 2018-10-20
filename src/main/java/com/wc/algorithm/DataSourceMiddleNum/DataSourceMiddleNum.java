package com.wc.algorithm.DataSourceMiddleNum;

import com.wc.ds.heap.ArrayHeap;

public class DataSourceMiddleNum {
    int[] a = new int[]{1,3,5,6,2,9,7,11,14,23,754,24,12};
    // 1 2 3 5 e763 7 9 11 12 14 23 24 754
    //maxHeap --- minHeap
    //最大堆在左,最大值比最小堆根小
    //最小堆在右,最小值比最大堆根大
    //两堆长度相差不超过1
    //125 786
    //1236789
    //125  786

    public void start(){
        ArrayHeap<Integer> maxHeap = new ArrayHeap<>(ArrayHeap.TYPE.MAX_HEAP);
        ArrayHeap<Integer> minHeap = new ArrayHeap<>(ArrayHeap.TYPE.MIN_HEAP);
        for (int data : a) {

            if(maxHeap.size() == 0){
                maxHeap.insert(data);
                continue;
            }
            if (minHeap.size() ==0 ){
                minHeap.insert(data);
                continue;
            }
            if( data < maxHeap.root()){
                maxHeap.insert(data);
            }else{
                minHeap.insert(data);
            }

            if(maxHeap.root() > minHeap.root()){
                minHeap.insert(maxHeap.delete());
            }

            int gap = maxHeap.size() - minHeap.size();
            if(gap == 2){
                minHeap.insert(maxHeap.delete());
            }

            if(gap == -2){
                maxHeap.insert(minHeap.delete());
            }
        }

        if (maxHeap.size() == minHeap.size()){
            System.out.println((maxHeap.root() + minHeap.root()) / 2);
        }

        if(maxHeap.size() > minHeap.size()){
            System.out.println(maxHeap.root());
        }

        if(minHeap.size() > maxHeap.size()){
            System.out.println(minHeap.root());
        }
    }

    public static void main(String[] args){
        new DataSourceMiddleNum().start();
    }
}


