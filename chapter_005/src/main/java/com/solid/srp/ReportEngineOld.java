package com.solid.srp;

import java.util.function.Predicate;

public class ReportEngineOld implements ReportEngine {
    private Store store;

    public ReportEngineOld(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired().getTime()).append(";")
                    .append(employee.getFired().getTime()).append(";")
                    .append(employee.getSalary()).append(";").append(System.lineSeparator());
        }
        return text.toString();
    }
}
