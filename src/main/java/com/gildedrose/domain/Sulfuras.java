package com.gildedrose.domain;

public record Sulfuras(FixedSellIn sellIn) implements LegendaryItem {

  private static final FixedQuality quality = FixedQuality.of(80);

  @Override
  public String name() {
    return "Sulfuras, Hand of Ragnaros";
  }

  @Override
  public Quality quality() {
    return quality;
  }
}
