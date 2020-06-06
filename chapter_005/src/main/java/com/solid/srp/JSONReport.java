package com.solid.srp;

import java.util.List;
import java.util.StringJoiner;

public class JSONReport implements ReportFormat {

    @Override
    public String make(List<Employee> eList, List<String> columns) {
        StringBuilder text = new StringBuilder();

        text.append("{\"employees\":[");

        for (Employee emp : eList) {
            text.append("{");
            StringJoiner sj = new StringJoiner(",");
            for (String col : columns) {
                sj.add(
                        String.format("\"%s\":\"%s\"", col, getEmployeeProperty(emp, col))
                );
            }
            text.append(sj.toString());
            text.append("}");
        }

        text.append("]}").append(System.lineSeparator());

        return text.toString();
    }
}
