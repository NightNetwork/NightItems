package me.ashydev.nightitems.item.types;

public interface Container<T> {
    T get();
    void set(T value);
}
