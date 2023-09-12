package me.ashydev.nightitems.item.component;

import me.ashydev.nightitems.item.types.Describable;

import java.util.List;

public interface DescribableComponent extends DrawableComponent, Describable {
    @Override
    default List<String> draw(List<String> input) {
        for (String s : description()) input.add("&7" + s);
        return input;
    }
}
