package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isSulfuras(item)) {
                if (isRegular(item)) {
                    if (notFullyDegradedYet(item)) {
                        decreaseQuality(item);
                    }

                    decreaseSalePeriod(item);

                    if (saleOver(item) && notFullyDegradedYet(item)) {
                        decreaseQuality(item);
                    }
                } else {
                    if (isAgedBrie(item)) {
                        if (notOptimalQualityYet(item)) {
                            increaseQuality(item);
                        }

                        decreaseSalePeriod(item);

                        if (saleOver(item) && notOptimalQualityYet(item)) {
                            increaseQuality(item);
                        }
                    } else if (isBackstagePasses(item)) {
                        if (notOptimalQualityYet(item)) {
                            increaseQuality(item);
                        }

                        if (withinTenDaysOfSellDate(item)) {
                            if (notOptimalQualityYet(item)) {
                                increaseQuality(item);
                            }
                        }

                        if (withinFiveDaysOfSellDate(item)) {
                            if (notOptimalQualityYet(item)) {
                                increaseQuality(item);
                            }
                        }

                        decreaseSalePeriod(item);

                        if (saleOver(item)) {
                            fullyDegrade(item);
                        }
                    }
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

    private boolean notFullyDegradedYet(Item item) {
        return item.quality > 0;
    }

    private void decreaseQuality(Item item) {
        item.quality -= 1;
    }

    private void increaseQuality(Item item) {
        item.quality += 1;
    }

    private void fullyDegrade(Item item) {
        item.quality = 0;
    }

    private boolean notOptimalQualityYet(Item item) {
        return item.quality < 50;
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