package me.ashydev.nightitems.item.types;

public class SimpleContainer<T> implements Container<T>, Convertible<String>, Nullable {
    private T value;

    public SimpleContainer(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public String convert() {
        return value.toString();
    }

    @Override
    public boolean isNull() {
        return value == null;
    }
}
