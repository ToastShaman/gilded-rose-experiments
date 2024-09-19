package com.gildedrose.modern;

import static java.util.Objects.requireNonNull;

import com.gildedrose.GildedRose;
import com.gildedrose.domain.InventoryAction;
import com.gildedrose.domain.ReadonlyItem;
import java.util.List;

public class GildedRoseModern implements GildedRose {

  private final List<ReadonlyItem> items;

  private final InventoryItemFactory factory;

  private final InventoryUpdater inventoryUpdater;

  private final InventoryActionPrinter printer;

  public GildedRoseModern(
      List<ReadonlyItem> items, InventoryItemFactory factory, InventoryUpdater inventoryUpdater) {
    this(items, factory, inventoryUpdater, InventoryActionPrinter.consolePrinter());
  }

  public GildedRoseModern(
      List<ReadonlyItem> items,
      InventoryItemFactory factory,
      InventoryUpdater inventoryUpdater,
      InventoryActionPrinter printer) {
    this.items = requireNonNull(items);
    this.factory = requireNonNull(factory);
    this.inventoryUpdater = requireNonNull(inventoryUpdater);
    this.printer = requireNonNull(printer);
  }

  @Override
  public void updateQuality() {
    var updates = items.stream().map(factory).map(inventoryUpdater).toList();

    printer.print(updates);

    for (InventoryAction action : updates) {
      action.run();
    }
  }
}
