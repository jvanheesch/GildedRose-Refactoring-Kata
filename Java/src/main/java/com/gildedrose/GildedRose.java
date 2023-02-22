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

            int qualityDecrease = getQualityDecrease(item);

            if (item.sellIn < 0) {
                qualityDecrease = 2 * qualityDecrease;
            }

            item.quality -= qualityDecrease;

            item.quality = Math.min(item.quality, MAXIMUM_QUALITY);
            item.quality = Math.max(item.quality, 0);
        }
    }

    private static int getQualityDecrease(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                return -1;
            case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                int sellIn = item.sellIn;
                if (sellIn < 5) {
                    return -3;
                }
                if (sellIn < 10) {
                    return -2;
                }
                return -1;
            default:
                return 1;
        }
    }
}
