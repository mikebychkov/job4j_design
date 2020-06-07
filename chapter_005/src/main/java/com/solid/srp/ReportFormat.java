package com.solid.srp;

import java.util.List;

public interface ReportFormat {

    String make(List<Employee> eList, List<String> columns);

    default String getEmployeeProperty(Employee emp, String propName) {
        switch (propName) {
            case "name": return emp.getName();
            case "hired": return emp.getHired().getTime().toString();
            case "fired": return emp.getFired().getTime().toString();
            case "salary": return String.valueOf(emp.getSalary());
            default: throw new IllegalArgumentException();
        }
    }
}
