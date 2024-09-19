package com.gildedrose;

import com.oneeyedmen.okeydoke.Approver;
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension;
import java.io.StringWriter;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@DisplayNameGeneration(ReplaceUnderscores.class)
class GildedRoseScenarioTest {

  @RegisterExtension ApprovalsExtension approvals = new ApprovalsExtension();

  List<Item> items =
      List.of(
          new Item("+5 Dexterity Vest", 10, 20),
          new Item("Aged Brie", 2, 0),
          new Item("Elixir of the Mongoose", 5, 7),
          new Item("Sulfuras, Hand of Ragnaros", 0, 80),
          new Item("Sulfuras, Hand of Ragnaros", -1, 80),
          new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
          new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
          new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
          // this conjured item does not work properly yet
          new Item("Conjured Mana Cake", 3, 6));

  @Test
  void updates_stock_items(Approver approver) {
    // given
    var days = 10;
    var writer = new StringWriter();
    var inventoryWriter = new DailyInventoryWriter(writer);
    var gildedRose = new GildedRose(items.toArray(new Item[0]));

    // when
    for (int i = 0; i < days; i++) {
      inventoryWriter.apply(items);
      gildedRose.updateQuality();
    }

    // then
    approver.assertApproved(writer);
  }
}
