package com.gildedrose;

class GildedRose {
    public static final int OPTIMUM_QUALITY = 50;
    public static final int FULLY_DEGRADED_QUALITY = 0;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isRegular(item)) {
                decreaseSalePeriod(item);
                decreaseQuality(item);
            } else if (isAgedBrie(item)) {
                decreaseSalePeriod(item);
                increaseQualityBy(item, saleOver(item) ? 2 : 1);
            } else if (isBackstagePasses(item)) {
                increaseQualityBy(item, 1);

                if (withinTenDaysOfSellDate(item)) {
                    increaseQualityBy(item, 1);
                }

                if (withinFiveDaysOfSellDate(item)) {
                    increaseQualityBy(item, 1);
                }

                decreaseSalePeriod(item);

                if (saleOver(item)) {
                    fullyDegrade(item);
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
        return item.sellIn < 11;
    }

    private boolean withinFiveDaysOfSellDate(Item item) {
        return item.sellIn < 6;
    }

    private boolean saleOver(Item item) {
        return item.sellIn < 0;
    }

    private void decreaseSalePeriod(Item item) {
        item.sellIn = item.sellIn - 1;
    }
}