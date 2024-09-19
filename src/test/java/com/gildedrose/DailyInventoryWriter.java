package com.gildedrose;

import static java.util.Objects.requireNonNull;

import com.gildedrose.legacy.Item;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicInteger;

public final class DailyInventoryWriter {

  private final AtomicInteger index = new AtomicInteger(0);

  private final Writer writer;

  public DailyInventoryWriter(Writer writer) {
    this.writer = requireNonNull(writer, "writer must not be null");
  }

  public void apply(Item[] items) {
    try {
      writer.write("-------- day %d --------\n".formatted(index.getAndIncrement()));
      writer.append("name, sellIn, quality\n");

      for (Item item : items) {
        writer.write("%s\n".formatted(item));
      }

      writer.write("\n");
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
