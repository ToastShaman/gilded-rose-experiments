package com.gildedrose.domain;

import static java.util.Objects.requireNonNull;

import com.gildedrose.legacy.Item;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ReadonlyItem {

  private final Item item;

  public ReadonlyItem(Item item) {
    this.item = requireNonNull(item);
  }

  public String name() {
    return item.name;
  }

  public int sellIn() {
    return item.sellIn;
  }

  public int quality() {
    return item.quality;
  }

  public Item mutable() {
    return item;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReadonlyItem that = (ReadonlyItem) o;
    return Objects.equals(item, that.item);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(item);
  }

  @Override
  public String toString() {
    return "ReadonlyItem[item=%s]".formatted(item);
  }

  public static ReadonlyItem of(Item item) {
    return new ReadonlyItem(item);
  }

  public static List<ReadonlyItem> from(Item[] items) {
    return Stream.of(items).map(ReadonlyItem::of).toList();
  }
}
