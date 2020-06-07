package com.solid.lsp.food;

import java.util.Calendar;

public class Cake extends Food {

    public Cake(String name, Calendar createDate, Calendar expireDate, double price) {
        super(name, createDate, expireDate, price);
    }

    public Cake(String name, Calendar createDate, Calendar expireDate, double price, byte discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
