package me.ashydev.nightitems.item.types;

public interface Serial<T> {

    T serialize(T input);
    void deserialize(T input);
}
