package com.gildedrose.domain;

public record BackstagePass(String name, UnboundedSellIn sellIn, BoundedQuality quality)
    implements Passes {

  public int daysBeforeConcert() {
    return sellIn.value();
  }
}
