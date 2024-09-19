package com.gildedrose.domain;

import static java.util.Objects.requireNonNull;

public record InventoryItem(ReadonlyItem readonly, PurchasableItem item) {

  public InventoryItem {
    requireNonNull(readonly);
    requireNonNull(item);
  }
}
