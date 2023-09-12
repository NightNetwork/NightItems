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

public class ImplBasicNightItem extends BasicNightItem {

    public ImplBasicNightItem(String name, Material material, List<ItemStat<?>> stats, ItemCategory category, ItemType type, Rarity rarity, String... description) {
        super(name, material, stats, category, type, rarity, description);
    }
}
