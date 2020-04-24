package com.list;

public class SimpleStack<T> {
    private SimpleList<T> linked = new SimpleList<T>();

    public T pop() {
        int lastInd = linked.size() - 1;
        T rsl = linked.get(lastInd);
        linked.remove(lastInd);
        return rsl;
    }

    public void push(T value) {
        this.linked.add(value);
    }

    public int size() {
        return this.linked.size();
    }
}
