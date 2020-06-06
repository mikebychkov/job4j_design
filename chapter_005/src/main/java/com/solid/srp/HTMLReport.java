package com.solid.srp;

import java.util.List;

public class HTMLReport implements ReportFormat {

    @Override
    public String make(List<Employee> eList, List<String> columns) {
        StringBuilder text = new StringBuilder();

        text.append("<!DOCTYPE html><html>")
                .append("<head><title>Employees</title></head><body>")
                .append("<h1>");

        for (String col : columns) {
            text.append(col.toUpperCase()).append(";");
        }

        text.append("</h1><br>");

        for (Employee emp : eList) {
            for (String col : columns) {
                text.append(getEmployeeProperty(emp, col)).append(";");
            }
            text.append("<br>");
        }

        text.append("</body></html>")
                .append(System.lineSeparator());

        return text.toString();
    }
}
