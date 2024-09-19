package com.gildedrose.domain;

import static java.util.Objects.requireNonNull;

public record InventoryAction(ReadonlyItem readonly, PurchasableItem before, PurchasableItem after)
    implements Runnable {

  public InventoryAction {
    requireNonNull(readonly);
    requireNonNull(before);
    requireNonNull(after);
  }

  @Override
  public void run() {
    var mutable = readonly.mutable();
    mutable.sellIn = after.sellIn().value();
    mutable.quality = after.quality().value();
  }
}
