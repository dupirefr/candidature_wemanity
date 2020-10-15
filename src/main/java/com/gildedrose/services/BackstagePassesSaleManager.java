package com.gildedrose.services;

import com.gildedrose.Item;

public class BackstagePassesSaleManager extends RegularItemSaleManager implements ItemSaleManager {
    public BackstagePassesSaleManager(Item item) {
        super(item);
    }

    @Override
    public void sell() {
        decreaseSalePeriod(item);
        if (saleOver(item)) {
            fullyDegrade(item);
        } else if (withinFiveDaysOfSellDate(item)) {
            increaseQualityBy(item, 3);
        } else if (withinTenDaysOfSellDate(item)) {
            increaseQualityBy(item, 2);
        } else {
            increaseQualityBy(item, 1);
        }
    }

    private void fullyDegrade(Item item) {
        item.quality = FULLY_DEGRADED_QUALITY;
    }

    private boolean withinTenDaysOfSellDate(Item item) {
        return item.sellIn < 10;
    }

    private boolean withinFiveDaysOfSellDate(Item item) {
        return item.sellIn < 5;
    }

    private void increaseQualityBy(Item item, int increaseSpeed) {
        item.quality = Math.min(item.quality + increaseSpeed, OPTIMUM_QUALITY);
    }
}
