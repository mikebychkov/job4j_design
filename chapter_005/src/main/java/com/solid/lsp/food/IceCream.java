package com.solid.lsp.food;

import com.solid.lsp.food.Food;

import java.util.Calendar;

public class IceCream extends Food {

    public IceCream(String name, Calendar createDate, Calendar expireDate, double price) {
        super(name, createDate, expireDate, price);
    }

    public IceCream(String name, Calendar createDate, Calendar expireDate, double price, byte discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
