package me.ashydev.nightitems.util;

import lombok.Getter;

@Getter
public class Pair<T, K> {
    public T first;
    public K second;

    public Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
        this(null, null);
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(K second) {
        this.second = second;
    }

    public static <T, K> Pair<T, K> of(T first, K second) {
        return new Pair<>(first, second);
    }
}
