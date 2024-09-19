package com.gildedrose.domain;

import java.util.function.UnaryOperator;

// spotless:off
public sealed interface PurchasableItem
    permits CommonItem, ConjuredItem, LegendaryItem, MaturedCheese, Passes {

  String name();

  SellIn sellIn();

  Quality quality();

  default PurchasableItem withQuality(UnaryOperator<BoundedQuality> f) {
    return switch (this) {
      case Sulfuras it -> it;
      case CommonItem(String n, UnboundedSellIn s, BoundedQuality q) -> new CommonItem(n, s, f.apply(q));
      case ConjuredItem(String n, UnboundedSellIn s, BoundedQuality q) -> new ConjuredItem(n, s, f.apply(q));
      case AgedBrie(UnboundedSellIn s, BoundedQuality q) -> new AgedBrie(s, f.apply(q));
      case BackstagePass(String n, UnboundedSellIn s, BoundedQuality q) -> new BackstagePass(n, s, f.apply(q));
    };
  }

  default PurchasableItem withSellIn(UnaryOperator<UnboundedSellIn> f) {
    return switch (this) {
      case Sulfuras it -> it;
      case CommonItem(String n, UnboundedSellIn s, BoundedQuality q) -> new CommonItem(n, f.apply(s), q);
      case ConjuredItem(String n, UnboundedSellIn s, BoundedQuality q) -> new ConjuredItem(n, f.apply(s), q);
      case AgedBrie(UnboundedSellIn s, BoundedQuality q) -> new AgedBrie(f.apply(s), q);
      case BackstagePass(String n, UnboundedSellIn s, BoundedQuality q) -> new BackstagePass(n, f.apply(s), q);
    };
  }
}
// spotless:on
