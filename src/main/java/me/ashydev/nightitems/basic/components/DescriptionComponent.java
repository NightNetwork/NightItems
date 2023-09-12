package me.ashydev.nightitems.basic.components;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import lombok.Getter;
import me.ashydev.nightitems.item.component.DescribableComponent;

@Getter
public class DescriptionComponent implements DescribableComponent {
    private String[] description;

    public DescriptionComponent(String... description) {
        this.description = description;
    }

    public DescriptionComponent(NBTCompound input) {
        deserialize(input);
    }

    @Override
    public String getName() {
        return "Description Component";
    }

    @Override
    public NBTCompound serialize(NBTCompound input) {
        input.setString("description", String.join(";", description));
        return input;
    }

    @Override
    public void deserialize(NBTCompound input) {
        description = input.getString("description").split(";");
    }

    @Override
    public String[] description() {
        return description;
    }
}
