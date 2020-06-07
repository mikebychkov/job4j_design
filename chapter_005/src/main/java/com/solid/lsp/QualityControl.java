package com.solid.lsp;

import com.solid.lsp.food.Food;
import com.solid.lsp.storage.Storage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class QualityControl {
    private Storage warehouse;
    private Storage shop;
    private Storage trash;
    private Calendar relocationDate = new GregorianCalendar();
    private List<Food> food = new ArrayList<>();

    public QualityControl(Storage warehouse, Storage shop, Storage trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
        this.relocationDate.setTimeInMillis(System.currentTimeMillis());
    }

    public void relocateFood(byte discountValue) {
        pollFood();
        for (Food fo : this.food) {
            byte percent = fo.getExpirationPercentage(relocationDate);
            if (this.warehouse.isPercentageFeats(percent)) {
                this.warehouse.add(fo);
            } else if (this.shop.isPercentageFeats(percent)) {
                this.shop.add(fo);
            } else if (this.shop.isPercentageFeatsOnDiscount(percent)) {
                fo.setDiscount(discountValue);
                this.shop.add(fo);
            } else {
                this.trash.add(fo);
            }
        }
        this.food.clear();
    }

    private void pollFood() {
        this.food.addAll(this.warehouse.pollAll());
        this.food.addAll(this.shop.pollAll());
    }
}
