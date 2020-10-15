package com.gildedrose;

import com.gildedrose.services.ItemSaleManager;

class GildedRose {
    private static final int OPTIMUM_QUALITY = 50;
    private static final int FULLY_DEGRADED_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemSaleManager saleManager = new ItemSaleManager();

            if (isRegular(item)) {
                saleManager.decreaseSalePeriod(item);
                decreaseQuality(item);
            } else if (isAgedBrie(item)) {
                saleManager.decreaseSalePeriod(item);
                increaseQualityBy(item, saleOver(item) ? 2 : 1);
            } else if (isBackstagePasses(item)) {
                saleManager.decreaseSalePeriod(item);
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

    private void decreaseQuality(Item item) {
        item.quality = Math.max(item.quality - decreaseSpeed(item), FULLY_DEGRADED_QUALITY);
    }

    private int decreaseSpeed(Item item) {
        return saleOver(item) ? 2 : 1;
    }

    private void increaseQualityBy(Item item, int increaseSpeed) {
        item.quality = Math.min(item.quality + increaseSpeed, OPTIMUM_QUALITY);
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

    private boolean saleOver(Item item) {
        return item.sellIn < 0;
    }
}