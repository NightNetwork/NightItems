package me.ashydev.nightitems.item;

import org.bukkit.inventory.ItemStack;

public interface ItemValidator<I extends NightItem> {
    boolean validate(ItemStack stack, I item);
}
