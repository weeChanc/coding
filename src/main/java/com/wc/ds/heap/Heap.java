package com.wc.ds.heap;

interface Heap<T extends Comparable<T>> {
    int size();

    int insert(T node);

    //删除是删除根节点
    T delete();

    T root();
    boolean isEmpty();

    void build(T ... nodes);
}