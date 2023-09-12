package me.ashydev.nightitems.item.component;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.util.List;

public interface ActionComponent extends DrawableComponent, DescribableComponent, Listener {

    String getAction();
    long getCoolDown();

    @Override
    default List<String> draw(List<String> input) {
        input.add("&#e8922aItem Ability! &#e8c33c(" + getAction() + ")");
        for (String s : description()) {
            input.add("&7" + s);
        }

        if (getCoolDown() > 0)
            input.add("&8Cooldown: " + getCoolDown() / 1000 + "s");

        return input;
    }

    @Override
    default NBTCompound serialize(NBTCompound input) {
        return input;
    }

    @Override
    default void deserialize(NBTCompound input) {
    }
}
