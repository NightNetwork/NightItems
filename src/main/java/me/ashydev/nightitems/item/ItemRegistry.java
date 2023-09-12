package me.ashydev.nightitems.item;

import it.unimi.dsi.fastutil.Hash;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ItemRegistry {
    private final HashMap<String, NightItem> items = new HashMap<>();

    public void register(NightItem item) {
        items.put(item.getId(), item);
    }

    public NightItem get(String id) {
        return items.get(id);
    }



}
