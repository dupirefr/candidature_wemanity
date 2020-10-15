package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void updateQuality_regularItems() {
        Item[] items = new Item[] {
          new Item("already_degraded_item", 0, 0)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertItemsEquals(new Item("already_degraded_item", -1, 0), app.items[0]);
    }

    void assertItemsEquals(Item expected, Item actual) {
        assertThat(actual.name).describedAs("Item name should be %s, but was %s", expected.name, actual.name).isEqualTo(expected.name);
        assertThat(actual.sellIn).describedAs("Item sell-in should be %d, but was %d", expected.sellIn, actual.sellIn).isEqualTo(expected.sellIn);
        assertThat(actual.quality).describedAs("Item quality should be %d, but was %d", actual.quality).isEqualTo(expected.quality);
    }
}
