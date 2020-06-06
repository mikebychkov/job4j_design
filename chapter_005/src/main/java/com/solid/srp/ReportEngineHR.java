package com.solid.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngineHR implements ReportEngine {
    private Store store;

    public ReportEngineHR(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        list.sort(
                (e1, e2) -> (int) (e2.getSalary() * 100 - e1.getSalary() * 100)
                );
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        for (Employee employee : list) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";").append(System.lineSeparator());
        }
        return text.toString();
    }
}
