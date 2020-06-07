package com.solid.lsp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import com.solid.lsp.food.Cake;
import com.solid.lsp.food.Cookies;
import com.solid.lsp.food.Food;
import com.solid.lsp.food.IceCream;
import com.solid.lsp.storage.Shop;
import com.solid.lsp.storage.Storage;
import com.solid.lsp.storage.Trash;
import com.solid.lsp.storage.Warehouse;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class QualityControlTest {

    Food bCake;
    Food iCream;
    Food crackers;
    Food crackersDiscount;
    Storage warehouse;
    Storage shop;
    Storage trash;
    QualityControl qc;

    @Before
    public void init() {

        Calendar cl1 = new GregorianCalendar();
        cl1.set(2020, 0, 1);

        Calendar cl3 = new GregorianCalendar();
        cl3.set(2020, 2, 1);

        Calendar cl6 = new GregorianCalendar();
        cl6.set(2020, 5, 1);

        Calendar cl7 = new GregorianCalendar();
        cl7.set(2020, 6, 1);

        Calendar cl9 = new GregorianCalendar();
        cl9.set(2020, 8, 1);

        Calendar cl11 = new GregorianCalendar();
        cl11.set(2020, 10, 1);

        //

        bCake = new Cake("Biscuit cake", cl6, cl9, 50);
        iCream = new IceCream("Chocolate ice cream", cl3, cl6, 50);
        crackers = new Cookies("Crackers", cl1, cl11, 50);
        crackersDiscount = new Cookies("Crackers (discount)", cl1, cl7, 50);

        List<Food> food = Arrays.asList(bCake, iCream, crackers, crackersDiscount);

        warehouse = new Warehouse(food);
        shop = new Shop();
        trash = new Trash();

        qc = new QualityControl(warehouse, shop, trash);
    }

    @Test
    public void relocateFoodWarehouse() {
        qc.relocateFood((byte) 50);
        assertThat(warehouse.getAll(), is(List.of(bCake)));
    }

    @Test
    public void relocateFoodShop() {
        qc.relocateFood((byte) 50);
        assertThat(shop.getAll(), is(List.of(crackers, crackersDiscount)));
    }

    @Test
    public void relocateFoodShopDiscount() {
        qc.relocateFood((byte) 50);
        assertThat(crackers.getDiscount(), is((byte) 0));
        assertThat(crackersDiscount.getDiscount(), is((byte) 50));
    }

    @Test
    public void relocateFoodTrash() {
        qc.relocateFood((byte) 50);
        assertThat(trash.getAll(), is(List.of(iCream)));
    }

    @Test
    public void testExpirationPercentage() {
        Calendar cl = new GregorianCalendar();
        cl.setTimeInMillis(System.currentTimeMillis());
        System.out.println(crackersDiscount.getExpirationPercentage(cl));
    }
}
