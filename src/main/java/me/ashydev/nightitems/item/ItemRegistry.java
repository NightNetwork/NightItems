package me.ashydev.nightitems.item;

import it.unimi.dsi.fastutil.Hash;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.miningsimulatorx.MiningSimulatorX;
import me.ashydev.miningsimulatorx.core.item.components.SimpleItemStat;
import me.ashydev.nightitems.item.component.ActionComponent;
import me.ashydev.nightitems.item.stats.ItemStat;
import org.bukkit.Bukkit;

import java.util.HashMap;

@Getter
@Setter
public class ItemRegistry {
    private final HashMap<String, NightItem> items;

    public ItemRegistry() {
        items = new HashMap<>();
    }


    public void register(NightItem item) {
        items.put(item.getId(), item);

        // register all actions
        item.getComponents().stream().filter(component -> component instanceof ActionComponent).map(component -> (ActionComponent) component).forEach(action -> Bukkit.getPluginManager().registerEvents(action, MiningSimulatorX.getInstance()));
    }

    public NightItem get(String id) {
        return items.get(id);
    }
}
