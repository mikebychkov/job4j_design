package com.solid.lsp.fooddistribution.storage;

import com.solid.lsp.fooddistribution.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class Storage {
    private List<Food> food;
    private byte expirePercentFrom;
    private byte expirePercentTo;
    private byte expirePercentFromOnDiscount;
    private byte expirePercentToOnDiscount;

    public Storage(List<Food> food,
                   byte expirePercentFrom, byte expirePercentTo,
                   byte expirePercentFromOnDiscount, byte expirePercentToOnDiscount) {

        this.food = new ArrayList<>(food);
        this.expirePercentFrom = expirePercentFrom;
        this.expirePercentTo = expirePercentTo;
        this.expirePercentFromOnDiscount = expirePercentFromOnDiscount;
        this.expirePercentToOnDiscount = expirePercentToOnDiscount;
    }

    public void add(Food f) {
        this.food.add(f);
    }

    public void remove(Food f) {
        this.food.remove(f);
    }

    public void removeAll() {
        this.food.clear();
    }

    public List<Food> getAll() {
        return this.food;
    }

    public List<Food> pollAll() {
        List<Food> rsl = new ArrayList<>(this.food);
        removeAll();
        return rsl;
    }

    public boolean isPercentageFeats(byte percent) {
        return percent >= this.expirePercentFrom && percent < this.expirePercentTo;
    }

    public boolean isPercentageFeatsOnDiscount(byte percent) {
        return percent >= this.expirePercentFromOnDiscount && percent < this.expirePercentToOnDiscount;
    }
}
