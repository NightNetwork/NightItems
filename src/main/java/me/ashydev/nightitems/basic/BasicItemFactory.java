package me.ashydev.nightitems.basic;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTCompoundList;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.nightitems.basic.components.DescriptionComponent;
import me.ashydev.nightitems.basic.components.InfoComponent;
import me.ashydev.nightitems.basic.stats.SimpleItemStat;
import me.ashydev.nightitems.item.ItemBuilder;
import me.ashydev.nightitems.item.ItemRegistry;
import me.ashydev.nightitems.item.ItemValidator;
import me.ashydev.nightitems.item.NightItem;
import me.ashydev.nightitems.item.component.ActionComponent;
import me.ashydev.nightitems.item.component.SerializableComponent;
import me.ashydev.nightitems.item.stats.ItemStat;
import me.ashydev.nightitems.item.types.Drawable;
import me.ashydev.nightitems.util.Color;
import me.ashydev.nightitems.util.Pair;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Getter
@Setter
public class BasicItemFactory implements ItemBuilder<BasicNightItem>, ItemValidator<BasicNightItem> {
    private ItemRegistry registry;
    private Plugin plugin;

    public BasicItemFactory(ItemRegistry registry, Plugin plugin) {
        this.registry = registry;
        this.plugin = plugin;
    }

    @Override
    public ItemStack build(BasicNightItem item) {
        ItemStack stack = new ItemStack(item.getMaterial());
        ItemMeta meta = stack.getItemMeta();

        meta.displayName(Color.colorize(item.draw("")));

        List<String> lore = new ArrayList<>();
        PriorityQueue<Pair<Drawable<List<String>>, Double>> drawables = item.getDrawables();

        Pair<Drawable<List<String>>, Double> drawable;

        while ((drawable = drawables.poll()) != null) {
            lore = drawable.getFirst().draw(lore);
        }

        List<Component> loreComp = new ArrayList<>();

        for (String str : lore)
            loreComp.add(Color.colorize(str));

        meta.lore(loreComp);
        stack.setItemMeta(meta);

        NBTItem nbt = new NBTItem(stack);

        NBTCompound compound = nbt.getCompound("night-item");
        compound.setString("id", item.getId());
        compound.setString("name", item.getName());
        compound.setString("material", item.getMaterial().name());

        List<SerializableComponent> components = item.getComponents().stream().filter(component -> component instanceof SerializableComponent).map(component -> (SerializableComponent) component).toList();

        for (SerializableComponent component : components) {
            compound = component.serialize(compound);
        }


        return nbt.getItem();
    }

    @Override
    public BasicNightItem getItem(ItemStack stack) {
        NBTItem nbt = new NBTItem(stack);

        NBTCompound compound = nbt.getCompound("night-item");
        String name = compound.getString("name");
        String id = compound.getString("id");
        Material material = Material.valueOf(compound.getString("material"));


        NightItem item = registry.get(id);

        InfoComponent info = new InfoComponent(compound);
        DescriptionComponent description = new DescriptionComponent(compound);
        NBTCompoundList stats = compound.getCompoundList("stats");

        List<ItemStat<?>> itemStats = new ArrayList<>();

        for (int i = 0; i < stats.size(); i++) {
            NBTCompound stat = stats.get(i);
            itemStats.add(SimpleItemStat.load(stat.getName(), stat));
        }

        BasicNightItem i = new ImplBasicNightItem(name, material, itemStats, info.getCategory(), info.getType(), info.getRarity(), description.getDescription());
        for (ActionComponent component : item.getComponents().stream().filter(component -> component instanceof ActionComponent).map(component -> (ActionComponent) component).toList()) {
            i.registerAction(component, plugin);
        }

        i.setupDrawables();


        return i;
    }

    @Override
    public BasicNightItem validate(ItemStack stack) {
        return getItem(stack);
    }
}
