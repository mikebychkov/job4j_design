package com.iterator;

import java.util.*;

public class IteratorOfIterators {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iit) {

        return new Iterator<Integer>() {
            private Iterator<Integer> it = Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                while (iit.hasNext() && !it.hasNext()) {
                    it = iit.next();
                }
                return it.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next();
            }
        };
    }
}
