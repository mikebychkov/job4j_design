package com.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class SimpleMap2<K, V> implements Iterable<Map.Entry<K, V>> {
    private DataEntry[] entries;
    private final int defGrowth = 16;
    private final float defFillIndex = 0.75F;
    private int modCount = 0;

    private class DataEntry<K, V> {
        Object key;
        Object value;

        public DataEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public SimpleMap2() {
        this.entries = new DataEntry[this.defGrowth];
    }

    public boolean insert(K key, V value) {
        int index = getKeyIndex(key);
        if (this.entries[index] != null) {
            return false;
        }
        this.entries[index] = new DataEntry(key, value);
        modCount++;
        checkMapFillAndRehash();
        return true;
    }

    public V get(K key) {
        int index = getKeyIndex(key);
        if (indexInvalid(index) || this.entries[index] == null) {
            throw new NoSuchElementException();
        }
        return (V) this.entries[index].value;
    }

    public boolean delete(K key) {
        int index = getKeyIndex(key);
        if (indexInvalid(index)) {
            return false;
        }
        this.entries[index] = null;
        return true;
    }

    private int getKeyIndex(K k) {
        return Math.abs(k.hashCode()) % this.entries.length;
    }

    private boolean indexInvalid(int index) {
        return index < 0 || index >= this.entries.length;
    }

    private void checkMapFillAndRehash() {
        float recentFillIndex = (float) this.modCount / this.entries.length;
        if (recentFillIndex < this.defFillIndex) {
            return;
        }
        DataEntry[] oldEntries = this.entries.clone();
        int newSize = this.entries.length * 2;
        this.entries = new DataEntry[newSize];
        this.modCount = 0;
        for (int i = 0; i < oldEntries.length; i++) {
            if (oldEntries[i] == null) {
                continue;
            }
            insert((K) oldEntries[i].key, (V) oldEntries[i].value);
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new SimpleIterator(this);
    }

    private class SimpleIterator implements Iterator<Map.Entry<K, V>> {
        private int pointer;
        SimpleMap2<K, V> sm;

        public SimpleIterator(SimpleMap2<K, V> sm) {
            this.sm = sm;
        }

        @Override
        public boolean hasNext() {
            for (int i = this.pointer; i < sm.entries.length; i++) {
                if (sm.entries[i] != null) {
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
            return new MapEntry(
                    (K) sm.entries[this.pointer].key,
                    (V) sm.entries[this.pointer++].value
            );
        }
    }

    private class MapEntry implements Map.Entry<K, V> {

        private K key;
        private V value;

        private MapEntry(K k, V v) {
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
