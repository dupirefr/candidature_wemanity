package com.gildedrose;

import com.gildedrose.services.ItemSaleManager;
import com.gildedrose.services.ItemSaleManagerFactory;

import java.util.Arrays;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
          .map(new ItemSaleManagerFactory()::saleManagerForItem)
          .forEach(ItemSaleManager::sell);
    }
}