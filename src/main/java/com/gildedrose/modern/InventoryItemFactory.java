package com.gildedrose.modern;

import com.gildedrose.domain.AgedBrie;
import com.gildedrose.domain.BackstagePass;
import com.gildedrose.domain.BoundedQuality;
import com.gildedrose.domain.CommonItem;
import com.gildedrose.domain.ConjuredItem;
import com.gildedrose.domain.FixedSellIn;
import com.gildedrose.domain.InventoryItem;
import com.gildedrose.domain.ReadonlyItem;
import com.gildedrose.domain.Sulfuras;
import com.gildedrose.domain.UnboundedSellIn;
import java.util.function.Function;

// spotless:off
public interface InventoryItemFactory extends Function<ReadonlyItem, InventoryItem> {

  static InventoryItemFactory create() {
    return item -> {
      var name = item.name();
      var sellIn = item.sellIn();
      var quality = item.quality();

      var purchasableItem = switch (name) {
        case "Sulfuras, Hand of Ragnaros" -> new Sulfuras(FixedSellIn.of(sellIn));
        case "Aged Brie" -> new AgedBrie(UnboundedSellIn.of(sellIn), BoundedQuality.of(quality));
        case "Backstage passes to a TAFKAL80ETC concert" -> new BackstagePass(name, UnboundedSellIn.of(sellIn), BoundedQuality.of(quality));
        case "Conjured Mana Cake" -> new ConjuredItem(name, UnboundedSellIn.of(sellIn), BoundedQuality.of(quality));
        default -> new CommonItem(name, UnboundedSellIn.of(sellIn), BoundedQuality.of(quality));
      };

      return new InventoryItem(item, purchasableItem);
    };
  }
}
// spotless:on
