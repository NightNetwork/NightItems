package me.ashydev.nightitems.item;

import me.ashydev.nightitems.item.component.ItemComponent;
import org.bukkit.Material;

import java.util.List;

public class ImplNightItem extends AbstractNightItem {
    public ImplNightItem(String name, Material material, boolean glowing, boolean unique, List<ItemComponent> components) {
        super(name, material, glowing, unique, components);
    }

    @Override
    public String draw(String input) {
        return getName();
    }
}
