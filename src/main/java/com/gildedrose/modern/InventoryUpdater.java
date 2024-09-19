package com.gildedrose.modern;

import static com.gildedrose.domain.BoundedQuality.adjustQuality;
import static com.gildedrose.domain.BoundedQuality.qualityOf;
import static com.gildedrose.domain.UnboundedSellIn.decreaseSellIn;

import com.gildedrose.domain.AgedBrie;
import com.gildedrose.domain.BackstagePass;
import com.gildedrose.domain.BoundedQuality;
import com.gildedrose.domain.CommonItem;
import com.gildedrose.domain.ConjuredItem;
import com.gildedrose.domain.InventoryAction;
import com.gildedrose.domain.InventoryItem;
import com.gildedrose.domain.PurchasableItem;
import com.gildedrose.domain.Sulfuras;
import com.gildedrose.domain.UnboundedSellIn;
import java.util.function.Function;

public interface InventoryUpdater extends Function<InventoryItem, InventoryAction> {

  Function<PurchasableItem, PurchasableItem> updateSellIn =
      item ->
          item.withSellIn(
              switch (item) {
                case CommonItem _, ConjuredItem _, AgedBrie _, BackstagePass _ -> decreaseSellIn();
                case Sulfuras _ -> UnboundedSellIn.noop();
              });

  Function<PurchasableItem, PurchasableItem> updateQuality =
      item ->
          item.withQuality(
              switch (item) {
                case Sulfuras _ -> BoundedQuality.noop();
                case CommonItem _ -> adjustQuality(-1);
                case ConjuredItem _ -> adjustQuality(-2);
                case AgedBrie it -> adjustQuality(it.isExpired() ? 2 : 1);
                case BackstagePass it ->
                    switch (it.daysBeforeConcert()) {
                      case int days when days < 0 -> qualityOf(0);
                      case int days when days < 5 -> adjustQuality(3);
                      case int days when days < 10 -> adjustQuality(2);
                      default -> adjustQuality(1);
                    };
              });

  static InventoryUpdater create() {
    return item -> {
      var readonly = item.readonly();
      var before = item.item();
      var after = updateSellIn.andThen(updateQuality).apply(before);
      return new InventoryAction(readonly, before, after);
    };
  }
}
