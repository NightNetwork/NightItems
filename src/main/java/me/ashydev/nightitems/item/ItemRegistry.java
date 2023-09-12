package me.ashydev.nightitems.item;

import it.unimi.dsi.fastutil.Hash;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.nightitems.item.component.ActionComponent;
import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.stats.ItemStat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

@Getter
@Setter
public class ItemRegistry {
    private final HashMap<String, NightItem> items;

    public ItemRegistry() {
        items = new HashMap<>();
    }


    public void register(NightItem item, Plugin plugin) {
        items.put(item.getId(), item);

        for (ItemComponent component : item.getComponents()) {
            if (component instanceof ActionComponent) {
                Bukkit.getPluginManager().registerEvents((ActionComponent) component, plugin);
            }
        }
    }

    public NightItem get(String id) {
        return items.get(id);
    }
}
