package me.ashydev.nightitems.basic.info;

import lombok.Getter;

@Getter
public enum ItemType {
    PICKAXE("Pickaxe", "#76d4e3", 0),
    AXE("Axe", "#f72840", 1),
    SHOVEL("Shovel", "#67ff38", 2),
    HOE("Hoe", "#ed882f", 3),
    SWORD("Sword", "#65daf7", 4),
    BOW("Bow", "#f78565", 5),
    ARMOR("Armor", "#981df0", 6),
    BLOCK("Block", "#ff5eda", 7),
    MISC("Misc", "#5c5c5c", 8),
    ;

    private final String name;
    private final String color;

    private final int id;

    ItemType(String name, String color, int id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }


}