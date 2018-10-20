package com.wc.ds.heap;

import java.util.LinkedList;
import java.util.List;


/**
 * 完全二叉树的性质:
 * 利用数组可以很好的比哦啊是完全二叉树
 * 当前节点为I 则 两个儿子节点位于 2 * I 与 2 * I + 1;
 * 父亲节点为 I/2;
 * 偶数在左,奇数在右
 *
 * @param <T>
 */


public class ArrayHeap<T extends Comparable<T>> implements Heap<T>{

    List<T> content = new LinkedList<>();
    private HeapStrategy<T> strategy ;

    public void print(){
        content.forEach( E -> {
            System.out.println(E);
        });
    }

    public enum  TYPE{
        MAX_HEAP, MIN_HEAP
    }
    //堆可以看做一个完全二叉树
    // 同时该完全二叉树满足双亲结点大于等于孩子结点（大顶堆）
    // 或者双亲结点小于等于孩子结点（小顶堆）。

    public ArrayHeap(TYPE type) {
        if(type == TYPE.MAX_HEAP){
            strategy = new MaxHeapStrategy<>(content);
        }else{
            strategy = new MinHeapStrategy<>(content);
        }
    }

    @Override
    public int size() {
        return strategy.size();
    }

    @Override
    public int insert(T node) {
        return strategy.insert(node);
    }

    @Override
    public T delete() {
        return strategy.delete();
    }

    @Override
    public T root() {
        return content.get(0);
    }

    @Override
    public boolean isEmpty() {
        return strategy.isEmpty();
    }

    @Override
    public void build(T... nodes) {
        strategy.build(nodes);
    }

}


