package com.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T rsl = findById(id);
        if (rsl != null) {
            this.mem.set(this.mem.indexOf(rsl), rsl);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T rsl = findById(id);
        if (rsl != null) {
            this.mem.remove(rsl);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return this.mem.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }
}
