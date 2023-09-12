package me.ashydev.nightitems.item;

import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.types.Drawable;
import me.ashydev.nightitems.item.types.Nameable;
import org.bukkit.Material;

import java.util.List;
import java.util.stream.Stream;

public interface NightItem extends Nameable, Drawable<String> {


    boolean glowing();
    boolean unique();

    Material getMaterial();
    List<ItemComponent> getComponents();

    default Stream<ItemComponent> getComponent(String id) {
        return getComponents().stream().filter(component -> component.getId().equals(id));
    }

    @SuppressWarnings("unchecked")
    default <T extends ItemComponent> Stream<T> getComponent(Class<? extends T> clazz) {
        return (Stream<T>) getComponents().stream().filter(component -> component.getClass().equals(clazz));
    }
}
