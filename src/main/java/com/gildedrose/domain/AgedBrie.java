package com.gildedrose.domain;

public record AgedBrie(UnboundedSellIn sellIn, BoundedQuality quality) implements MaturedCheese {

  @Override
  public String name() {
    return "Aged Brie";
  }

  public boolean isExpired() {
    return sellIn.isExpired();
  }
}
