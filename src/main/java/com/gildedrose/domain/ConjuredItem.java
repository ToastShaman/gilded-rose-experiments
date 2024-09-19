package com.gildedrose.domain;

public record ConjuredItem(String name, UnboundedSellIn sellIn, BoundedQuality quality)
    implements PurchasableItem {}
