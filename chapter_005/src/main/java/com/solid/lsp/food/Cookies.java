package com.solid.lsp.food;

import java.util.Calendar;

public class Cookies extends Food {

    public Cookies(String name, Calendar createDate, Calendar expireDate, double price) {
        super(name, createDate, expireDate, price);
    }

    public Cookies(String name, Calendar createDate, Calendar expireDate, double price, byte discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
