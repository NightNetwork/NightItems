package me.ashydev.nightitems.item.component;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.ashydev.nightitems.item.types.Drawable;
import me.ashydev.nightitems.item.types.Nameable;
import me.ashydev.nightitems.item.types.Serial;

import java.util.List;

public interface ItemComponent extends Nameable, SerializableComponent{

    @Override
    default NBTCompound serialize(NBTCompound input) { return input; }

    @Override
    default void deserialize(NBTCompound input) { }
    @Override
    default String getId() {
        return getName().toLowerCase().replace(" ", "-");
    }
}
