package com.exam.minicmd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ShellTest {

    @Test
    public void test() {
        final Shell shell = new Shell();
        assertThat(shell.path().equals("/"), is(true));

        shell.cd("/");
        assertThat(shell.path().equals("/"), is(true));

        shell.cd("usr/..");
        assertThat(shell.path().equals("/"), is(true));

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assertThat(shell.path().equals("/usr/local"), is(true));

        shell.cd("..");
        assertThat(shell.path().equals("/usr"), is(true));

        shell.cd("//lib///");
        assertThat(shell.path().equals("/usr/lib"), is(true));
    }
}
