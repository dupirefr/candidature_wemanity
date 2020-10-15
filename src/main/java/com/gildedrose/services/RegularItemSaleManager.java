package com.gildedrose.services;

import com.gildedrose.Item;

public class RegularItemSaleManager implements ItemSaleManager {
    protected final Item item;

    public RegularItemSaleManager(Item item) {
        this.item = item;
    }

    @Override
    public void sell() {
        decreaseSalePeriod(item);
        decreaseQualityBy(item, decreaseSpeed(item));
    }

    protected final void decreaseSalePeriod(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    protected final void decreaseQualityBy(Item item, int decreaseSpeed) {
        item.quality = Math.max(item.quality - decreaseSpeed, FULLY_DEGRADED_QUALITY);
    }

    protected final int decreaseSpeed(Item item) {
        return saleOver(item) ? 2 : 1;
    }

    protected final boolean saleOver(Item item) {
        return item.sellIn < 0;
    }
}
