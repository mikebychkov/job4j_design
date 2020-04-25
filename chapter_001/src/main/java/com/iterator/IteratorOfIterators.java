package com.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorOfIterators {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iit) {
        return new Iterator<Integer>() {
            Iterator<Integer> it = iit.next();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer rsl = it.next();
                if (!it.hasNext() && iit.hasNext()) {
                    it = iit.next();
                }
                return rsl;
            }
        };
    }
}
