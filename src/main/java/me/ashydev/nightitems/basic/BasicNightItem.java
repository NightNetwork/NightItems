package me.ashydev.nightitems.basic;

import me.ashydev.nightitems.basic.components.DescriptionComponent;
import me.ashydev.nightitems.basic.components.InfoComponent;
import me.ashydev.nightitems.basic.info.ItemCategory;
import me.ashydev.nightitems.basic.info.ItemType;
import me.ashydev.nightitems.basic.info.Rarity;
import me.ashydev.nightitems.item.AbstractNightItem;
import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.stats.ItemStat;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicNightItem extends AbstractNightItem {
    public BasicNightItem(String name, Material material, List<ItemStat<?>> stats, ItemCategory category, ItemType type, Rarity rarity, String... description) {
        super(name, material, of(stats, category, type, rarity, description));
    }

    @Override
    public String draw(String input) {
        return getRarity().getColor() + getName();
    }

    public Rarity getRarity() {
        return getComponent(InfoComponent.class).getRarity();
    }

    public ItemType getType() {
        return getComponent(InfoComponent.class).getType();
    }

    public ItemCategory getCategory() {
        return getComponent(InfoComponent.class).getCategory();
    }

    public static List<ItemComponent> of(List<ItemStat<?>> stats, ItemCategory category, ItemType type, Rarity rarity, String... description) {
        List<ItemComponent> components = new ArrayList<>(stats);

        components.add(new DescriptionComponent(description));
        components.add(new InfoComponent(rarity, category, type));

        return components;
    }
}
