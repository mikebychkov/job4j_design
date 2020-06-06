package com.solid.srp;

import java.util.function.Predicate;

public class ReportEngineDev implements ReportEngine {
    private Store store;

    public ReportEngineDev(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();

        text.append("<!DOCTYPE html><html>")
                .append("<head><title>Report</title></head><body>")
                .append("<h1>Name; Hired; Fired; Salary;</h1><br>");

        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired().getTime()).append(";")
                    .append(employee.getFired().getTime()).append(";")
                    .append(employee.getSalary()).append(";<br>");
        }

        text.append("</body></html>")
                .append(System.lineSeparator());

        return text.toString();
    }
}
