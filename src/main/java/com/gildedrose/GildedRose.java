package com.gildedrose;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }

            if (item.name.equals("Aged Brie")) {
                updateAgedBrie(item);
            } else if (item.name.contains("Backstage passes")) {
                updateBackstagePass(item);
            } else if (item.name.contains("Conjured")) {
                updateConjuredItem(item);
            } else {
                updateNormalItem(item);
            }
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item, 1);
        item.sellIn--;
        if (item.sellIn < 0) {
            decreaseQuality(item, 1);
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item, 1);
        item.sellIn--;
        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }

    private void updateBackstagePass(Item item) {
        if (item.sellIn > 10) {
            increaseQuality(item, 1);
        } else if (item.sellIn > 5) {
            increaseQuality(item, 2);
        } else if (item.sellIn > 0) {
            increaseQuality(item, 3);
        } else {
            item.quality = 0;
        }
        item.sellIn--;
    }

    private void updateConjuredItem(Item item) {
        decreaseQuality(item, 2);
        item.sellIn--;
        if (item.sellIn < 0) {
            decreaseQuality(item, 2);
        }
    }

    private void increaseQuality(Item item, int amount) {
        if (item.quality < 50) {
            item.quality = Math.min(item.quality + amount, 50);
        }
    }

    private void decreaseQuality(Item item, int amount) {
        if (item.quality > 0) {
            item.quality = Math.max(item.quality - amount, 0);
        }
    }
}

