package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {
    @DisplayName("Update quality rules test for:")
    @ParameterizedTest(name = "{0}")
    @MethodSource("dataSet")
    void updateQuality(String description, Item[] itemsInTheStore, List<Item> expectedItems) {
        GildedRose app = new GildedRose(itemsInTheStore);
        app.updateQuality();

        assertItemsEqual(expectedItems, Arrays.asList(itemsInTheStore));
    }

    private static Stream<Arguments> dataSet() {
        return Stream.of(
          regularItemsDataSet(),
          agedBrieDataSet()
        );
    }

    private static Arguments regularItemsDataSet() {
        return Arguments.arguments(
          "Regular items",
          new Item[]{
            new Item("Still valid, undegraded item", 1, 2),
            new Item("Still valid, degraded item", 1, 0),
            new Item("Not valid anymore, undegraded item", 0, 2),
            new Item("Not valid anymore, degraded item", 0, 0)
          },
          Arrays.asList(
            new Item("Still valid, undegraded item", 0, 1),
            new Item("Still valid, degraded item", 0, 0),
            new Item("Not valid anymore, undegraded item", -1, 0),
            new Item("Not valid anymore, degraded item", -1, 0)
          )
        );
    }

    private static Arguments agedBrieDataSet() {
        return Arguments.arguments(
          "Aged Brie",
          new Item[]{
            new Item("Aged Brie", 1, 0),
            new Item("Aged Brie", 0, 0),
            new Item("Aged Brie", 0, 50)
          },
          Arrays.asList(
            new Item("Aged Brie", 0, 1),
            new Item("Aged Brie", -1, 2),
            new Item("Aged Brie", -1, 50)
          )
        );
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
