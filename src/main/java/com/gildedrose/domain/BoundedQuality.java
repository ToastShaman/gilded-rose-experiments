package com.gildedrose.domain;

import java.util.function.UnaryOperator;

public record BoundedQuality(int value) implements Quality {

  public BoundedQuality {
    if (value < 0 || value > 50) {
      throw new IllegalArgumentException("quality must be between 0-50");
    }
  }

  public static UnaryOperator<BoundedQuality> noop() {
    return it -> it;
  }

  public static UnaryOperator<BoundedQuality> adjustQuality(int adjustment) {
    return it -> new BoundedQuality(Math.max(0, Math.min(50, it.value + adjustment)));
  }

  public static UnaryOperator<BoundedQuality> qualityOf(int value) {
    return _ -> new BoundedQuality(value);
  }

  public static BoundedQuality of(int value) {
    return new BoundedQuality(value);
  }
}
