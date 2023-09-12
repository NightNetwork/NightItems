package me.ashydev.nightitems.item;

import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.types.Drawable;
import me.ashydev.nightitems.item.types.Nameable;
import org.bukkit.Material;

import java.util.List;

public interface NightItem extends Nameable, Drawable<String> {

    Material getMaterial();
    List<ItemComponent> getComponents();

    default ItemComponent getComponent(String id) {
        return getComponents().stream().filter(component -> component.getId().equals(id)).findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    default <T extends ItemComponent> T getComponent(Class<? extends T> clazz) {
        return (T) getComponents().stream().filter(component -> component.getClass().equals(clazz)).findFirst().orElse(null);
    }
}
