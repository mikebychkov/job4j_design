package com.solid.srp;

import java.util.function.Predicate;

public interface ReportEngine {
    String generate(Predicate<Employee> filter);
}
