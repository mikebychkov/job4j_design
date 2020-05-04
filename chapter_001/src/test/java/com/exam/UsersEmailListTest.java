package com.exam;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UsersEmailListTest {

    @Test
    public void test1() {
        Map<String, String> userEmails = new HashMap<>();
        userEmails.put("user1", "xxx@ya.ru,foo@gmail.com,lol@mail.ru");
        userEmails.put("user2", "foo@gmail.com,ups@pisem.net");
        userEmails.put("user3", "xyz@pisem.net,vasya@pupkin.com");
        userEmails.put("user4", "ups@pisem.net,aaa@bbb.ru");
        userEmails.put("user5", "xyz@pisem.net");

        Map<String, String> rsl = UsersEmailList.correctEmailList(userEmails);
        System.out.println(rsl);

        assertThat(rsl.get("user1"), is("xxx@ya.ru,foo@gmail.com,lol@mail.ru,ups@pisem.net,aaa@bbb.ru"));
        assertThat(rsl.get("user3"), is("xyz@pisem.net,vasya@pupkin.com"));
    }
}
