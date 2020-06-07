package com.solid.lsp.storage;

import com.solid.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Storage {

    public Shop(List<Food> food) {
        super(food, (byte) 25, (byte) 75, (byte) 75, (byte) 100);
    }

    public Shop() {
        this(new ArrayList<>());
    }
}
