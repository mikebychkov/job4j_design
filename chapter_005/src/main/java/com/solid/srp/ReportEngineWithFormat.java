package com.solid.srp;

import java.util.List;
import java.util.function.Predicate;

public interface ReportEngineWithFormat extends ReportEngine {

    String generate(Predicate<Employee> filter, List<String> columns, ReportFormat format);
}
