package me.ashydev.nightitems.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.nightitems.item.component.ActionComponent;
import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.component.SerializableComponent;
import me.ashydev.nightitems.item.stats.ItemStat;
import me.ashydev.nightitems.item.types.Drawable;
import me.ashydev.nightitems.util.Color;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ItemFactory implements ItemBuilder<NightItem>, ItemValidator<NightItem> {
    private ItemRegistry registry;
    private Plugin plugin;

    public ItemFactory(ItemRegistry registry, Plugin plugin) {
        this.registry = registry;
        this.plugin = plugin;
    }

    @Override
    public ItemStack build(NightItem item) {
        ItemStack stack = new ItemStack(item.getMaterial());
        ItemMeta meta = stack.getItemMeta();

        meta.displayName(Color.colorize(item.draw("")));

        List<String> lore = new ArrayList<>();
        List<Drawable<List<String>>> drawables = item.getComponents().stream().filter(component -> component instanceof Drawable).map(component -> (Drawable<List<String>>) component).toList();
    
        for (int i = 0; i < drawables.size(); i++) {
            Drawable<List<String>> drawable = drawables.get(i);
            if (drawable == null) continue;
            lore.addAll(drawable.draw(new ArrayList<>()));

            if (i != drawables.size() - 1) lore.add("");
        }

        List<Component> loreComp = new ArrayList<>();

        for (String str : lore) loreComp.add(Color.colorize(str));

        meta.lore(loreComp);

        if (item.glowing()) {
            meta.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 69, true);
            meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS);
        }

        meta.setUnbreakable(true);
        meta.addItemFlags(org.bukkit.inventory.ItemFlag.HIDE_UNBREAKABLE);

        stack.setItemMeta(meta);

        NBTItem nbt = new NBTItem(stack);

        NBTCompound compound = nbt.getOrCreateCompound("night-item");

        if (item.unique()) compound.setUUID("uuid", java.util.UUID.randomUUID());

        compound.setString("id", item.getId());
        compound.setString("name", item.getName());
        compound.setString("material", item.getMaterial().name());

        List<SerializableComponent> components = item.getComponents().stream().filter(Objects::nonNull).map(component -> (SerializableComponent) component).toList();

        for (SerializableComponent component : components) {
            compound = component.serialize(compound);
        }


        return nbt.getItem();
    }

    @Override
    public NightItem getItem(ItemStack stack) {
        NBTItem nbt = new NBTItem(stack);

        NBTCompound compound = nbt.getCompound("night-item");
        String name = compound.getString("name");
        String id = compound.getString("id");
        Material material = Material.valueOf(compound.getString("material"));


        NightItem item = registry.get(id);

        List<ItemStat<?>> itemStats = new ArrayList<>();

        AbstractNightItem i = new ImplNightItem(name, material, item.glowing(), item.unique(), item.getComponents());
        i.setupDrawables();


        return i;
    }

    public ItemStack update(ItemStack stack) {
        NightItem item = getItem(stack);
        return build(item);
    }

    @Override
    public boolean validate(ItemStack stack, NightItem item) {
        NBTItem nbt = new NBTItem(stack);
        NBTCompound compound = nbt.getCompound("night-item");
        if (compound == null) return false;
        String id = compound.getString("id");
        if (id == null) return false;
        return id.equals(item.getId());
    }

    public boolean validate(ItemStack stack, String item) {
        NBTItem nbt = new NBTItem(stack);
        NBTCompound compound = nbt.getCompound("night-item");
        if (compound == null) return false;
        String id = compound.getString("id");
        if (id == null) return false;
        return id.equals(item);
    }

    public boolean isValid(ItemStack stack) {
        if (stack == null || stack.getType() == Material.AIR) return false;

        NBTItem nbt = new NBTItem(stack);
        NBTCompound compound = nbt.getCompound("night-item");

        if (compound == null) return false;

        String id = compound.getString("id");

        if (id == null) return false;

        return registry.get(id) != null;
    }
}
