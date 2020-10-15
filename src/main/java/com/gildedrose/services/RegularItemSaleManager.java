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
        decreaseQuality(item);
    }

    protected void decreaseSalePeriod(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void decreaseQuality(Item item) {
        item.quality = Math.max(item.quality - decreaseSpeed(item), FULLY_DEGRADED_QUALITY);
    }

    private int decreaseSpeed(Item item) {
        return saleOver(item) ? 2 : 1;
    }

    protected boolean saleOver(Item item) {
        return item.sellIn < 0;
    }
}
