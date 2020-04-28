package com.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private Object[] keys;
    private Object[] values;
    private final int defGrowth = 16;
    private final float defFillIndex = 0.75F;
    private int modCount = 0;

    public SimpleMap() {
        this.keys = new Object[this.defGrowth];
        this.values = new Object[this.defGrowth];
    }

    public boolean insert(K key, V value) {
        int index = getKeyIndex(key);
        if (this.keys[index] != null) {
            return false;
        }
        this.keys[index] = key;
        this.values[index] = value;
        modCount++;
        checkMapFillAndRehash();
        return true;
    }

    public V get(K key) {
        int index = getKeyIndex(key);
        if (indexInvalid(index) || this.keys[index] == null) {
            throw new NoSuchElementException();
        }
        return (V) this.values[index];
    }

    public boolean delete(K key) {
        int index = getKeyIndex(key);
        if (indexInvalid(index)) {
            return false;
        }
        this.keys[index] = null;
        this.values[index] = null;
        return true;
    }

    private int getKeyIndex(K k) {
        return Math.abs(k.hashCode()) % this.keys.length;
    }

    private boolean indexInvalid(int index) {
        return index < 0 || index >= this.keys.length;
    }

    private void checkMapFillAndRehash() {
        float recentFillIndex = (float) this.modCount / this.keys.length;
        if (recentFillIndex < this.defFillIndex) {
            return;
        }
        Object[] oldKeys = this.keys.clone();
        Object[] oldValues = this.values.clone();
        int newSize = this.keys.length * 2;
        this.keys = new Object[newSize];
        this.values = new Object[newSize];
        this.modCount = 0;
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] == null) {
                continue;
            }
            insert((K) oldKeys[i], (V) oldValues[i]);
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new SimpleIterator(this);
    }

    private class SimpleIterator implements Iterator<Map.Entry<K, V>> {
        private int pointer;
        SimpleMap<K, V> sm;

        public SimpleIterator(SimpleMap<K, V> sm) {
            this.sm = sm;
        }

        @Override
        public boolean hasNext() {
            for (int i = this.pointer; i < sm.keys.length; i++) {
                if (sm.keys[i] != null) {
                    this.pointer = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return new Entry((K) sm.keys[this.pointer], (V) sm.values[this.pointer++]);
        }
    }

    private class Entry implements Map.Entry<K, V> {

        private K key;
        private V value;

        private Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
