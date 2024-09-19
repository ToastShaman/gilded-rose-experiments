package com.gildedrose.domain;

public sealed interface SellIn permits FixedSellIn, UnboundedSellIn {

  int value();
}
