package com.exam;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        for (User usr : previous) {
            int index = current.indexOf(usr);
            if (index < 0) {
                rsl.deleted++;
                continue;
            }
            if (!current.get(index).name.equals(usr.name)) {
                rsl.changed++;
            }
        }
        for (User usr : current) {
            int index = previous.indexOf(usr);
            if (index < 0) {
                rsl.added++;
            }
        }
        return rsl;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed; // id == id && name != name
        int deleted;
    }

}
