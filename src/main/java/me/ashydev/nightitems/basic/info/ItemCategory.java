package me.ashydev.nightitems.basic.info;

import lombok.Getter;

@Getter
public enum ItemCategory {
    NONE("None", "#ffffff", 0),
    ;

    private final String name;
    private final String color;

    private final int id;

    ItemCategory(String name, String color, int id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

}
