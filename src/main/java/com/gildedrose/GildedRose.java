package com.gildedrose;

import java.util.Collections;
import java.util.List;

class GildedRose {
    List<Item> items;
    static final String AGED_BRIE = "Aged Brie";
    static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public GildedRose(Item[] items) {
        this.items = List.of(items);
    }

    public void updateQuality() {
        items.forEach(item -> {

            switch (item.name) {
                case AGED_BRIE:
                    item = processQualityUnder50(item);
                    item.sellIn--;
                    if (isNegativeValue(item) && item.quality < 50) {
                        item.quality++;
                    }
                    break;
                case BACKSTAGE_PASS:
                    if (item.quality < 50) {
                        item.quality++;
                        item = processQualityUnder50(item);
                    }
                    item.sellIn--;
                    if (isNegativeValue(item)) {
                        item.quality = 0;
                    }
                    break;
                case SULFURAS:
                    decreaseQuality(item);
                    break;
                default:
                    decreaseQuality(item);
                    item.sellIn--;
                    if (isNegativeValue(item) && isaBoolean(item)) {
                        item.quality--;
                    }
                    break;

            }
        });

    }

    public Item processQualityUnder50(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.name.equals(BACKSTAGE_PASS)) {
                if (item.sellIn < 11) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
        return item;
    }

    private void decreaseQuality(Item item) {
        if (isaBoolean(item)) {
            if (!item.name.equals(SULFURAS)) {
                item.quality = item.quality - 1;
            }
        }
    }

    private boolean isNegativeValue(Item item) {
        return item.sellIn < 0;
    }

    private boolean isaBoolean(Item item) {
        return item.quality > 0;
    }

}
