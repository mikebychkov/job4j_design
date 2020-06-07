package com.solid.lsp.food;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Food: describes food entity
 * members:
 *  name: food name
 *  expireDate: food expiration date
 *  createDate: food creation date
 *  price: food price
 *  discount: food discount percent (%)
 */
public abstract class Food {
    private String name;
    private Calendar createDate;
    private Calendar expireDate;
    private double price;
    private byte discount;

    public Food(String name, Calendar createDate, Calendar expiration, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expiration;
        this.price = price;
    }

    public Food(String name, Calendar createDate, Calendar expireDate, double price, byte discount) {
        this(name, createDate, expireDate, price);
        this.discount = discount;
    }

    public byte getExpirationPercentage(Calendar onDate) {
        long totalMillis = this.expireDate.getTimeInMillis() - this.createDate.getTimeInMillis();
        long onDateMillis = onDate.getTimeInMillis() - this.createDate.getTimeInMillis();
        byte rsl = (byte) ((double) onDateMillis / totalMillis * 100);
        return rsl;
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", expireDate=" + expireDate.getTime() +
                ", createDate=" + createDate.getTime() +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
