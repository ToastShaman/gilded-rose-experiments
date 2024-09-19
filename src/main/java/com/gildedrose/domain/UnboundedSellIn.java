package com.gildedrose.domain;

import java.util.function.UnaryOperator;

public record UnboundedSellIn(int value) implements SellIn {

  public boolean isExpired() {
    return value < 0;
  }

  public static UnaryOperator<UnboundedSellIn> noop() {
    return it -> it;
  }

  public static UnaryOperator<UnboundedSellIn> decreaseSellIn() {
    return it -> new UnboundedSellIn(it.value - 1);
  }

  public static UnboundedSellIn of(int value) {
    return new UnboundedSellIn(value);
  }
}
