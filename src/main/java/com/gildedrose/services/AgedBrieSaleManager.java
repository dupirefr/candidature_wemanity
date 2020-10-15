package com.gildedrose.services;

import com.gildedrose.Item;

public class AgedBrieSaleManager extends RegularItemSaleManager implements ItemSaleManager {
    public AgedBrieSaleManager(Item item) {
        super(item);
    }

    @Override
    public void sell() {
        decreaseSalePeriod(item);
        increaseQualityBy(item, saleOver(item) ? 2 : 1);
    }

    private void increaseQualityBy(Item item, int increaseSpeed) {
        item.quality = Math.min(item.quality + increaseSpeed, OPTIMUM_QUALITY);
    }
}
