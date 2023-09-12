package me.ashydev.nightitems.item.types;

public interface Nullable {
    boolean isNull();

    default boolean isNotNull() {
        return !isNull();
    }
}
