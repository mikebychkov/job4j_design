package com.solid.lsp.storage;

import com.solid.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash extends Storage {

    public Trash(List<Food> food) {
        super(food, (byte) 100, (byte) 127, (byte) 0, (byte) 0);
    }

    public Trash() {
        this(new ArrayList<>());
    }
}
