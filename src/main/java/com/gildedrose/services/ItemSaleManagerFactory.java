package com.gildedrose.services;

import com.gildedrose.Item;

public class ItemSaleManagerFactory {
    public ItemSaleManager saleManagerForItem(Item item) {
        if (isRegular(item)) {
            return new RegularItemSaleManager(item);
        } else if (isAgedBrie(item)) {
            return new AgedBrieSaleManager(item);
        } else if (isBackstagePasses(item)) {
            return new BackstagePassesSaleManager(item);
        } else if (isSulfuras(item)) {
            return new SulfurasSaleManager();
        } else {
            throw new IllegalArgumentException("No sale manager exists for item named: " + item.name);
        }
    }

    private boolean isRegular(Item item) {
        return !isAgedBrie(item) && !isBackstagePasses(item) && !isSulfuras(item);
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
}
