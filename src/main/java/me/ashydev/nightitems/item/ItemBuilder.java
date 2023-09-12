package me.ashydev.nightitems.item;

import org.bukkit.inventory.ItemStack;

public interface ItemBuilder<I extends NightItem> {
    ItemStack build(I item);
    I getItem(ItemStack stack);
}
