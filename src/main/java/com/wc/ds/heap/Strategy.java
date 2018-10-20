package com.wc.ds.heap;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

abstract class HeapStrategy<T extends Comparable<T>> implements Heap<T> {

    private List<T> content;

    HeapStrategy(List<T> content) {
        this.content = content;
    }

    @Override
    public int size() {
        return content.size();
    }

    @Override
    public T root() {
        return content.get(0);
    }

    void swap(int from, int to) {
        T fromVal = content.get(from);
        T toVal = content.get(to);
        content.set(from, toVal);
        content.set(to, fromVal);
    }

    T getNode(int index) {
        if (index > size() - 1) return null;
        return content.get(index);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int insert(T node) {
        content.add(node);
        for (int i = content.size() / 2 - 1; i >= 0; i--) {
            swapIfNeeded(i);
        }
        return 0;
    }

    @Override
    public T delete() {
        if (size() == 0) return null;
        swap(0, size() - 1);
        T removed = content.remove(size() - 1);
        swapIfNeeded(0);
        return removed;
    }

    @Override
    public void build(T... nodes) {
        content.addAll(Arrays.asList(nodes));
        for (int i = content.size() / 2 - 1; i >= 0; i--) {
            swapIfNeeded(i);
        }
    }

    abstract void swapIfNeeded(int index);
}

class MaxHeapStrategy<T extends Comparable<T>> extends HeapStrategy<T> {

    public MaxHeapStrategy(List<T> content) {
        super(content);
    }


    @Override
    void swapIfNeeded(int index) {
        int maxIndex = maxNodeIndex(index * 2 + 1, index * 2 + 2);
        if (maxIndex == -1) return;
        if (maxNodeIndex(index, maxIndex) == maxIndex) {
            swap(index, maxIndex);
            swapIfNeeded(maxIndex);
        }
    }

    private int maxNodeIndex(int index1, int index2) {
        T node1 = getNode(index1);
        T node2 = getNode(index2);
        if (node1 == null && node2 == null) return -1;
        if (node2 == null) return index1;
        if (node1.compareTo(node2) > 0) return index1;
        return index2;
    }
}

class MinHeapStrategy<T extends Comparable<T>> extends HeapStrategy<T> {

    public MinHeapStrategy(List<T> content) {
        super(content);
    }

    @Override
    void swapIfNeeded(int index) {
        int maxIndex = minNodeIndex(index * 2 + 1, index * 2 + 2);
        if (maxIndex == -1) return;
        if (minNodeIndex(index, maxIndex) == maxIndex) {
            swap(index, maxIndex);
            swapIfNeeded(maxIndex);
        }
    }

    private int minNodeIndex(int index1, int index2) {
        T node1 = getNode(index1);
        T node2 = getNode(index2);
        if (node1 == null && node2 == null) return -1;
        if (node2 == null) return index1;
        if (node1.compareTo(node2) < 0) return index1;
        return index2;
    }
}