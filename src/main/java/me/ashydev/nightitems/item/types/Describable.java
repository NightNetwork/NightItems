package me.ashydev.nightitems.item.types;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public interface Describable {
    String[] description();

    default String getAsString() {
        StringBuilder builder = new StringBuilder();
        for (String line : description()) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }

    default String getLine(int line) {
        return description()[line];
    }

    default int getLineCount() {
        return description().length;
    }

    default boolean isEmpty() {
        return getLineCount() == 0;
    }

    default boolean isBlank() {
        AtomicBoolean blank = new AtomicBoolean(true);
        Arrays.asList(description()).forEach(line -> { blank.set(blank.get() & line.isBlank()); });
        return isEmpty() || blank.get();
    }
}
