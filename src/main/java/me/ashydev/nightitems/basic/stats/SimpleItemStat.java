package me.ashydev.nightitems.basic.stats;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.ashydev.nightitems.item.stats.AbstractItemStat;
import me.ashydev.nightitems.item.types.OperatorPosition;
import me.ashydev.nightitems.util.Color;

public class SimpleItemStat<T> extends AbstractItemStat<T> {
    public SimpleItemStat(String name, Color color, String operator, OperatorPosition position, String... description) {
        super(name, color, operator, position, description);
    }
    public SimpleItemStat(T value, String name, Color color, String operator, OperatorPosition position, String... description) {
        super(name, color, operator, position, description);
    }

    @Override
    public NBTCompound serialize(NBTCompound input) {
        NBTCompound compound = input.getOrCreateCompound("stats");
        NBTCompound stat = compound.getOrCreateCompound(getId());

        stat.setString("name", getName());
        stat.setObject("value", get());
        stat.setString("color", getColor().getHex());
        stat.setString("operator", getOperator());
        stat.setString("position", getPosition().name());
        StringBuilder builder = new StringBuilder();

        for (String s : getDescription()) {
            builder.append(s).append(";");
        }

        stat.setString("description", builder.toString());


        return input;
    }

    @Override
    public void deserialize(NBTCompound input) { }

    @SuppressWarnings("unchecked")
    public static <T> SimpleItemStat<T> load(String id, NBTCompound input) {
        NBTCompound compound = input.getCompound("stats");
        NBTCompound stat = compound.getCompound(id);

        String name = stat.getString("name");
        T value = (T) stat.getObject("value", Object.class);
        Color color = Color.fromHex(stat.getString("color"));
        String operator = stat.getString("operator");
        OperatorPosition position = OperatorPosition.valueOf(stat.getString("position"));
        String[] description = stat.getString("description").split(";");

        return new SimpleItemStat<>(value, name, color, operator, position, description);
    }
}
