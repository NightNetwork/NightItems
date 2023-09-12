package me.ashydev.nightitems.item.types;

public interface Nameable {
    String getName();

    default String getId() {
        return getName().toUpperCase().replace(" ", "_");
    }
}
