package com.gildedrose.services;

import com.gildedrose.Item;

public class ConjuredManaCakeSaleManager extends RegularItemSaleManager implements ItemSaleManager {
    public ConjuredManaCakeSaleManager(Item item) {
        super(item);
    }

    @Override
    public void sell() {
        decreaseSalePeriod(item);
        decreaseQualityBy(item, 2 * decreaseSpeed(item));
    }
}
