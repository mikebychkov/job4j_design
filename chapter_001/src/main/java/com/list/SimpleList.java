package com.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleList<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;

    private SimpleList<T>.Node first;
    private SimpleList<T>.Node last;

    public SimpleList() {
    }

    public void add(T model) {
        Node n = new Node(model);
        connectNode(this, n);
        this.last = n;
        if (this.first == null) {
            this.first = n;
        }
        this.size++;
        this.modCount++;
    }

    public T get(int index) {
        Node rsl = getNode(index);
        if (rsl == null) {
            throw new NoSuchElementException();
        }
        return rsl.value;
    }

    private Node getNode(int index) {
        if (index < getHalfSize()) {
            return getNodeAsc(index);
        } else {
            return getNodeDesc(index);
        }
    }

    private Node getNodeAsc(int index) {
        Node n = this.first;
        for (int i = 0; i <= getHalfSize(); i++) {
            if (i == index) {
                return n;
            }
            n = n.next;
        }
        return null;
    }

    private Node getNodeDesc(int index) {
        Node n = this.last;
        for (int i = this.size - 1; i > getHalfSize(); i--) {
            if (i == index) {
                return n;
            }
            n = n.prev;
        }
        return null;
    }

    private int getHalfSize() {
        return this.size / 2 + 1;
    }

    private class Node {
        T value;
        Node prev;
        Node next;
        public Node(T value) {
            this.value = value;
        }
    }

    private void connectNode(SimpleList<T> sl, Node n) {
        n.prev = sl.last;
        if (sl.last != null) {
            sl.last.next = n;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator(this);
    }

    private class SimpleIterator implements Iterator<T> {

        private SimpleList<T> sl;
        private SimpleList<T>.Node resent;
        private int modCount = 0;

        public SimpleIterator(SimpleList<T> sl) {
            this.sl = sl;
            this.resent = new Node(null);
            this.resent.next = sl.first;
            this.modCount = sl.modCount;
        }

        @Override
        public boolean hasNext() {
            return this.resent.next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.modCount != sl.modCount) {
                throw new ConcurrentModificationException();
            }
            this.resent = this.resent.next;
            return this.resent.value;
        }
    }
}
