package com.gildedrose.services;

import com.gildedrose.Item;

public class ItemSaleManagerFactory {
    public ItemSaleManager saleManagerForItem(Item item) {
        if (isAgedBrie(item)) {
            return new AgedBrieSaleManager(item);
        } else if (isBackstagePasses(item)) {
            return new BackstagePassesSaleManager(item);
        } else if (isSulfuras(item)) {
            return new SulfurasSaleManager();
        } else if (isConjured(item)) {
            return new ConjuredManaCakeSaleManager(item);
        } else {
            return new RegularItemSaleManager(item);
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isConjured(Item item) {
        return item.name.equals("Conjured Mana Cake");
    }
}
