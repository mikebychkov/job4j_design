package com.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import static com.exam.Analize.User;
import static com.exam.Analize.Info;

public class AnalizeTest {

    Analize an;
    User usr1;
    User usr2;
    User usr3;
    User usr4;
    User usr5;
    List<User> list1;
    List<User> list2;

    @Before
    public void setup() {
        an = new Analize();
        usr1 = new User(1, "Ivan");
        usr2 = new User(2, "Oleg");
        usr3 = new User(3, "Lena");
        usr4 = new User(4, "Ruslan");
        usr5 = new User(5, "Katya");
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    @Test
    public void twoAddedOneChangedOneDeleted() {
        list1.add(usr1);
        list1.add(usr2);
        list1.add(usr3);

        list2.add(usr1);
        list2.add(usr5);
        list2.add(new User(3, "Elena"));
        list2.add(usr4);

        Info rsl = an.diff(list1, list2);

        assertThat(rsl.added, is(2));
        assertThat(rsl.changed, is(1));
        assertThat(rsl.deleted, is(1));
    }

    @Test
    public void treeDeleted() {
        list1.add(usr1);
        list1.add(usr2);
        list1.add(usr3);

        Info rsl = an.diff(list1, list2);

        assertThat(rsl.deleted, is(3));
    }

    @Test
    public void twoChanged() {
        list1.add(usr3);
        list1.add(usr5);

        list2.add(new User(3, "Elena"));
        list2.add(new User(5, "Ekaterina"));

        Info rsl = an.diff(list1, list2);

        assertThat(rsl.changed, is(2));
    }

    @Test
    public void twoAdded() {
        list1.add(usr1);
        list1.add(usr2);
        list1.add(usr3);

        list2.add(usr1);
        list2.add(usr2);
        list2.add(usr3);
        list2.add(usr4);
        list2.add(usr5);

        Info rsl = an.diff(list1, list2);

        assertThat(rsl.added, is(2));
    }
}
