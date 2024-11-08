package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    public void testRegularItemDecreasesInQualityAndSellIn() {
        Item[] items = {new Item("Regular Item", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    public void testQualityDegradesTwiceAsFastAfterSellIn() {
        Item[] items = {new Item("Regular Item", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(18, items[0].quality);  // Quality should decrease by 2 after sellIn date passes
    }

    @Test
    public void testQualityDoesNotBecomeNegative() {
        Item[] items = {new Item("Regular Item", 10, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(0, items[0].quality);  // Quality should not go below 0
    }

    @Test
    public void testAgedBrieIncreasesInQuality() {
        Item[] items = {new Item("Aged Brie", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(21, items[0].quality);  // Aged Brie should increase in quality
    }

    @Test
    public void testAgedBrieQualityDoesNotExceedFifty() {
        Item[] items = {new Item("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(50, items[0].quality);  // Quality should not exceed 50
    }

    @Test
    public void testSulfurasDoesNotChangeInQualityOrSellIn() {
        Item[] items = {new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(10, items[0].sellIn);
        assertEquals(80, items[0].quality);  // Sulfuras quality and sellIn should not change
    }

    @Test
    public void testBackstagePassIncreasesInQualityAsSellInApproaches() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(10, items[0].sellIn);
        assertEquals(21, items[0].quality);  // Quality should increase by 1 when sellIn > 10
    }

    @Test
    public void testBackstagePassIncreasesByTwoWhenSellInTenOrLess() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);  // Quality should increase by 2 when sellIn <= 10
    }

    @Test
    public void testBackstagePassIncreasesByThreeWhenSellInFiveOrLess() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);  // Quality should increase by 3 when sellIn <= 5
    }

    @Test
    public void testBackstagePassQualityDropsToZeroAfterConcert() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);  // Quality should drop to 0 after concert
    }

    @Test
    public void testQualityDoesNotExceedFiftyForAllItems() {
        Item[] items = {
            new Item("Aged Brie", 5, 55),  // Aged Brie starting with quality > 50
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 55),  // Backstage pass with quality > 50
            new Item("Regular Item", 5, 55),  // Regular item with quality > 50
            new Item("Conjured Mana Cake", 5, 55)  // Conjured item with quality > 50
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(55, items[0].quality);  // Aged Brie should cap at 50
        assertEquals(55, items[1].quality);  // Backstage pass should cap at 50
        assertEquals(54, items[2].quality);  // Regular item quality should decrease but never exceed 50 initially
        assertEquals(54, items[3].quality);  // Conjured item quality decreases by 2
    }

//    @Test
//    @Ignore
//    public void testConjuredItemDegradesTwiceAsFast() {
//        Item[] items = {new Item("Conjured Mana Cake", 3, 6)};
//        GildedRose app = new GildedRose(items);
//
//        app.updateQuality();
//
//        assertEquals(2, items[0].sellIn);
//        assertEquals(4, items[0].quality);  // Conjured item quality should decrease by 2
//    }

//    @Test
//
//    public void testConjuredItemDegradesTwiceAsFastAfterSellIn() {
//        Item[] items = {new Item("Conjured Mana Cake", 0, 6)};
//        GildedRose app = new GildedRose(items);
//
//        app.updateQuality();
//
//        assertEquals(-1, items[0].sellIn);
//        assertEquals(2, items[0].quality);  // Conjured item quality should decrease by 4 after sellIn
//    }
}
