package com.gildedrose.services;

import com.gildedrose.Item;

public class ItemSaleManager {
    public void decreaseSalePeriod(Item item) {
        item.sellIn = item.sellIn - 1;
    }
}
