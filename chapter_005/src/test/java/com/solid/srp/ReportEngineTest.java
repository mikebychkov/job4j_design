package com.solid.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();

        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);

        ReportEngine engine = new ReportEngineOld(store);

        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired().getTime()).append(";")
                .append(worker.getFired().getTime()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenDevelopersGenerated() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();

        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);

        ReportEngine engine = new ReportEngineDev(store);

        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html><html>")
                .append("<head><title>Report</title></head><body>")
                .append("<h1>Name; Hired; Fired; Salary;</h1><br>")
                .append(worker.getName()).append(";")
                .append(worker.getHired().getTime()).append(";")
                .append(worker.getFired().getTime()).append(";")
                .append(worker.getSalary()).append(";<br>")
                .append("</body></html>")
                .append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();

        Calendar now = Calendar.getInstance();

        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Semen", now, now, 150);
        store.add(worker1);
        store.add(worker2);

        ReportEngine engine = new ReportEngineHR(store);

        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
