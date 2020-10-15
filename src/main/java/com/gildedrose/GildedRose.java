package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isRegular(item)) {
                if (notFullyDegradedYet(item)) {
                    decreaseQuality(item);
                }
            } else {
                if (notOptimalQuality(item)) {
                    increaseQuality(item);

                    if (isBackstagePasses(item)) {
                        if (item.sellIn < 11) {
                            if (notOptimalQuality(item)) {
                                increaseQuality(item);
                            }
                        }

                        if (item.sellIn < 6) {
                            if (notOptimalQuality(item)) {
                                increaseQuality(item);
                            }
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackstagePasses(item)) {
                        if (notFullyDegradedYet(item)) {
                            if (!isSulfuras(item)) {
                                decreaseQuality(item);
                            }
                        }
                    } else {
                        fullyDegrade(item);
                    }
                } else {
                    if (notOptimalQuality(item)) {
                        increaseQuality(item);
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

    private boolean notOptimalQuality(Item item) {
        return item.quality < 50;
    }
}