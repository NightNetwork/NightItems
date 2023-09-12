package me.ashydev.nightitems.basic.components;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import lombok.Getter;
import me.ashydev.nightitems.basic.info.ItemCategory;
import me.ashydev.nightitems.basic.info.ItemType;
import me.ashydev.nightitems.basic.info.Rarity;
import me.ashydev.nightitems.item.component.DrawableComponent;
import me.ashydev.nightitems.item.component.ItemComponent;

import java.util.List;

@Getter
public class InfoComponent implements DrawableComponent {
    private Rarity rarity;
    private ItemCategory category;
    private ItemType type;

    public InfoComponent(Rarity rarity, ItemCategory category, ItemType type) {
        this.rarity = rarity;
        this.category = category;
        this.type = type;
    }

    public InfoComponent(NBTCompound input) {
        deserialize(input);
    }

    @Override
    public List<String> draw(List<String> input) {
        input.add(rarity.getColor() + "&l" + rarity.getName() + " &7(" + category.getName() + ") (" + type.getName() + ")");
        return input;
    }

    @Override
    public String getName() {
        return "Info Component";
    }

    @Override
    public NBTCompound serialize(NBTCompound input) {
        input.setString("rarity", rarity.name());
        input.setString("category", category.name());
        input.setString("type", type.name());

        return input;
    }

    @Override
    public void deserialize(NBTCompound input) {
        rarity = Rarity.valueOf(input.getString("rarity"));
        category = ItemCategory.valueOf(input.getString("category"));
        type = ItemType.valueOf(input.getString("type"));
    }
}
