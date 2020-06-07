package com.solid.lsp.fooddistribution.storage;

import com.solid.lsp.fooddistribution.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Storage {

    public Warehouse(List<Food> food) {
        super(food, (byte) 0, (byte) 25, (byte) 0, (byte) 0);
    }

    public Warehouse() {
        this(new ArrayList<>());
    }
}
