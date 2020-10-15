package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void updateQuality_regularItems() {
        Item[] itemsInTheStore = new Item[]{
          new Item("Still valid, undegraded item", 1, 2),
          new Item("Still valid, degraded item", 1, 0),
          new Item("Not valid anymore, undegraded item", 0, 2),
          new Item("Not valid anymore, degraded item", 0, 0)
        };

        GildedRose app = new GildedRose(itemsInTheStore);
        app.updateQuality();

        List<Item> expectedItems = Arrays.asList(
          new Item("Still valid, undegraded item", 0, 1),
          new Item("Still valid, degraded item", 0, 0),
          new Item("Not valid anymore, undegraded item", -1, 0),
          new Item("Not valid anymore, degraded item", -1, 0)
        );

        assertItemsEqual(expectedItems, Arrays.asList(itemsInTheStore));
    }

    void assertItemsEqual(List<Item> expected, List<Item> actual) {
        assertThat(actual).describedAs("There should be the same number of actual than expected items").hasSameSizeAs(expected);
        for (int i = 0; i < actual.size(); i++) {
           assertItemsEqual(expected.get(i), actual.get(i));
        }
    }

    void assertItemsEqual(Item expected, Item actual) {
        assertThat(actual.name)
          .describedAs("Item '%s' name should be %s, but was %s", actual.name, expected.name, actual.name)
          .isEqualTo(expected.name);

        assertThat(actual.sellIn)
          .describedAs("Item '%s' sell-in should be %d, but was %d", actual.name, expected.sellIn, actual.sellIn)
          .isEqualTo(expected.sellIn);

        assertThat(actual.quality)
          .describedAs("Item '%s' quality should be %d, but was %d", actual.name, expected.quality, actual.quality)
          .isEqualTo(expected.quality);
    }
}
