package com.tdd.kissdryyagny;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T compareByPredicate(List<T> value, BiPredicate<T, T> pr) {
        Iterator<T> it = value.iterator();
        T rsl = null;
        if (it.hasNext()) {
            rsl = it.next();
        }
        while (it.hasNext()) {
            T comp = it.next();
            if (pr.test(rsl, comp)) {
                rsl = comp;
            }
        }
        return rsl;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compareByPredicate(value,
                (t1, t2) -> comparator.compare(t1, t2) < 0
        );
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compareByPredicate(value,
                (t1, t2) -> comparator.compare(t1, t2) > 0
        );
    }
}
