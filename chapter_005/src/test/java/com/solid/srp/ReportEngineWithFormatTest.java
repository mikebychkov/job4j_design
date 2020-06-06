package com.solid.srp;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportEngineWithFormatTest {

    Employee worker;
    MemStore store;
    ReportEngineWithFormat engine;
    List<String> columns;

    @Before
    public void init() {
        store = new MemStore();

        Calendar now = Calendar.getInstance();

        worker = new Employee("Ivan", now, now, 100);
        store.add(worker);

        engine = new ReportEngineFormat(store);

        columns = List.of("name", "hired", "fired", "salary");
    }

    @Test
    public void whenJSONGenerated() {

        StringBuilder expect = new StringBuilder()
                .append("{\"employees\":[{")
                .append(String.format("\"name\":\"%s\"", worker.getName())).append(",")
                .append(String.format("\"hired\":\"%s\"", worker.getHired().getTime())).append(",")
                .append(String.format("\"fired\":\"%s\"", worker.getFired().getTime())).append(",")
                .append(String.format("\"salary\":\"%s\"", worker.getSalary()))
                .append("}]}")
                .append(System.lineSeparator());

        ReportFormat format = new JSONReport();

        assertThat(engine.generate(em -> true, columns, format), is(expect.toString()));
    }

    @Test
    public void whenXMLGenerated() {
        StringBuilder expect = new StringBuilder()
                .append("<employees><employee ")
                .append(String.format("name=\"%s\" ", worker.getName()))
                .append(String.format("hired=\"%s\" ", worker.getHired().getTime()))
                .append(String.format("fired=\"%s\" ", worker.getFired().getTime()))
                .append(String.format("salary=\"%s\" ", worker.getSalary()))
                .append("/></employees>")
                .append(System.lineSeparator());

        ReportFormat format = new XMLReport();

        assertThat(engine.generate(em -> true, columns, format), is(expect.toString()));
    }

    @Test
    public void whenHTMLGenerated() {
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html><html>")
                .append("<head><title>Employees</title></head><body>")
                .append("<h1>NAME;HIRED;FIRED;SALARY;</h1><br>")
                .append(worker.getName()).append(";")
                .append(worker.getHired().getTime()).append(";")
                .append(worker.getFired().getTime()).append(";")
                .append(worker.getSalary()).append(";")
                .append("<br></body></html>")
                .append(System.lineSeparator());

        ReportFormat format = new HTMLReport();

        assertThat(engine.generate(em -> true, columns, format), is(expect.toString()));
    }
}
