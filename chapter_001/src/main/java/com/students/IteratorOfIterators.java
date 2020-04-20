package com.students;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorOfIterators {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iit) {
        List<Integer> list = new ArrayList<>();
        while (iit.hasNext()) {
            Iterator<Integer> it = iit.next();
            while (it.hasNext()) {
                list.add(it.next());
            }
        }
        return list.iterator();
    }
}
