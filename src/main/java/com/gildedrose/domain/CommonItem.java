package com.gildedrose.domain;

public record CommonItem(String name, UnboundedSellIn sellIn, BoundedQuality quality)
    implements PurchasableItem {}
