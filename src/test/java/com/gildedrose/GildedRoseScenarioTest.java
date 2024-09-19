package com.gildedrose;

import static org.junit.jupiter.params.provider.EnumSource.Mode.INCLUDE;

import com.gildedrose.domain.ReadonlyItem;
import com.gildedrose.legacy.GildedRoseLegacy;
import com.gildedrose.legacy.Item;
import com.gildedrose.modern.GildedRoseModern;
import com.gildedrose.modern.InventoryItemFactory;
import com.gildedrose.modern.InventoryUpdater;
import com.oneeyedmen.okeydoke.Approver;
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension;
import java.io.StringWriter;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class GildedRoseScenarioTest {

  @RegisterExtension ApprovalsExtension approvals = new ApprovalsExtension();

  Item[] items =
      new Item[] {
        new Item("+5 Dexterity Vest", 10, 20),
        new Item("Aged Brie", 2, 0),
        new Item("Elixir of the Mongoose", 5, 7),
        new Item("Sulfuras, Hand of Ragnaros", 0, 80),
        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
        // this conjured item does not work properly yet
        new Item("Conjured Mana Cake", 3, 6)
      };

  enum Version {
    LEGACY,
    MODERN
  }

  @ParameterizedTest
  @EnumSource(
      value = Version.class,
      mode = INCLUDE,
      names = {"MODERN"})
  void updates_stock_items(Version version, Approver approver) {
    // given
    var days = 10;
    var writer = new StringWriter();
    var inventoryWriter = new DailyInventoryWriter(writer);
    var gildedRose =
        switch (version) {
          case LEGACY -> new GildedRoseLegacy(items);
          case MODERN ->
              new GildedRoseModern(
                  ReadonlyItem.from(items),
                  InventoryItemFactory.create(),
                  InventoryUpdater.create());
        };

    // when
    for (int i = 0; i < days; i++) {
      inventoryWriter.apply(items);
      gildedRose.updateQuality();
    }

    // then
    approver.assertApproved(writer);
  }
}
