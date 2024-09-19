package com.gildedrose.domain;

public record FixedSellIn(int value) implements SellIn {

  public static FixedSellIn of(int value) {
    return new FixedSellIn(value);
  }
}
