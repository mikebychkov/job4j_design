package com.solid.lsp.fooddistribution;

import com.solid.lsp.fooddistribution.food.Food;
import com.solid.lsp.fooddistribution.storage.Storage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class QualityControl {
    private List<Storage> storages;
    private Calendar relocationDate = new GregorianCalendar();
    private List<Food> food = new ArrayList<>();

    public QualityControl(List<Storage> storages) {
        this.storages = storages;
        this.relocationDate.setTimeInMillis(System.currentTimeMillis());
    }

    public void relocateFood(byte discountValue) {
        pollFood();
        for (Food fo : this.food) {
            byte percent = fo.getExpirationPercentage(relocationDate);
            for (Storage store : this.storages) {
                if (store.isPercentageFeats(percent)) {
                    store.add(fo);
                } else if (store.isPercentageFeatsOnDiscount(percent)) {
                    fo.setDiscount(discountValue);
                    store.add(fo);
                }
            }
        }
        this.food.clear();
    }

    private void pollFood() {
        for (Storage store : this.storages) {
            this.food.addAll(store.pollAll());
        }
    }
}
