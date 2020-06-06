package com.solid.srp;

import java.util.List;

public class XMLReport implements ReportFormat {

    @Override
    public String make(List<Employee> eList, List<String> columns) {
        StringBuilder text = new StringBuilder();

        text.append("<employees>");

        for (Employee emp : eList) {
            text.append("<employee ");
            for (String col : columns) {
                text.append(
                        String.format("%s=\"%s\" ", col, getEmployeeProperty(emp, col))
                );
            }
            text.append("/>");
        }

        text.append("</employees>").append(System.lineSeparator());

        return text.toString();
    }
}
