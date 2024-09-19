package com.gildedrose.modern;

import com.gildedrose.domain.InventoryAction;
import java.util.List;

public interface InventoryActionPrinter {

  void print(List<InventoryAction> action);

  static InventoryActionPrinter consolePrinter() {
    return actions -> {
      String format = "%-50s %-10s %-10s%n";

      System.out.printf(format, "Name", "SellIn", "Quality");

      actions.forEach(
          action ->
              System.out.printf(
                  format,
                  action.readonly().name(),
                  action.before().sellIn().value(),
                  action.after().quality().value()));

      System.out.printf(format, "-".repeat(50), "-".repeat(10), "-".repeat(10));
    };
  }
}
