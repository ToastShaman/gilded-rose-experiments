package com.gildedrose.domain;

public record FixedQuality(int value) implements Quality {

  public FixedQuality {
    if (value < 0) {
      throw new IllegalArgumentException("quality must be greater than 0");
    }
  }

  public static FixedQuality of(int quality) {
    return new FixedQuality(quality);
  }
}
