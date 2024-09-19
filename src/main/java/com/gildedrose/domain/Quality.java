package com.gildedrose.domain;

public sealed interface Quality permits FixedQuality, BoundedQuality {

  int value();
}
