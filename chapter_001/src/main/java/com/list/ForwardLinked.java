package com.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void deleteFirst() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        this.head = this.head.next;
    }

    public void revert() {
        if (this.head == null) {
            return;
        }

        Node<T> tmp = new Node<>(this.head.value, null);

        Node<T> n = this.head;
        while (n.next != null) {
            // n
            Node<T> nn = new Node<>(n.next.value, tmp);
            tmp = nn;
            //
            n = n.next;
        }
        this.head = tmp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
