package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    // TODO: reconsider name, as sulfurs has quality 80
    private static final int MAXIMUM_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                continue;
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0 && item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                item.quality = 0;
                continue;
            }

            if (item.name.equals(AGED_BRIE)
                || item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                item.quality = item.quality + 1;

                if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    if (item.sellIn < 10) {
                        item.quality = item.quality + 1;
                    }

                    if (item.sellIn < 5) {
                        item.quality = item.quality + 1;
                    }
                }
            } else if (item.quality > 0) {
                item.quality = item.quality - 1;
            }

            if (item.sellIn < 0) {
                if (item.name.equals(AGED_BRIE)) {
                    if (item.quality < MAXIMUM_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                } else {
                    item.quality = item.quality - 1;
                }
            }

            item.quality = Math.min(item.quality, MAXIMUM_QUALITY);
            item.quality = Math.max(item.quality, 0);
        }
    }
}
