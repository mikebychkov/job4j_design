package com.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        Map<Integer, User> userIdMap = current.stream().collect(
                Collectors.toMap(usr -> usr.id, usr -> usr)
        );
        for (User usr : previous) {
            User usrCur = userIdMap.get(usr.id);
            if (usrCur == null) {
                rsl.deleted++;
                continue;
            }
            if (!usrCur.name.equals(usr.name)) {
                rsl.changed++;
            }
        }
        rsl.added = current.size() - (previous.size() - rsl.deleted);
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
