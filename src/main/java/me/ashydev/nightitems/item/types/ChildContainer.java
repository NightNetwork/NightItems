package me.ashydev.nightitems.item.types;

import java.util.List;

public interface ChildContainer<T> {

    List<T> getChildren();

    default void addChild(T child) {
        getChildren().add(child);
    }

    default void removeChild(T child) {
        getChildren().remove(child);
    }

    default boolean hasChild(T child) {
        return getChildren().contains(child);
    }

    default T getChild(int index) {
        return getChildren().get(index);
    }

    default boolean hasChildren() {
        return !getChildren().isEmpty();
    }

    default int getChildCount() {
        return getChildren().size();
    }
}
