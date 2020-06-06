package com.solid.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngineFormat implements ReportEngineWithFormat {
    private Store store;

    public ReportEngineFormat(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String generate(Predicate<Employee> filter, List<String> columns, ReportFormat format) {
        List<Employee> eList = store.findBy(filter);
        return format.make(eList, columns);
    }
}
