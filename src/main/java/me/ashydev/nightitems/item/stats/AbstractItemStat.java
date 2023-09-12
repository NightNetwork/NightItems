package me.ashydev.nightitems.item.stats;

import lombok.Getter;
import lombok.Setter;
import me.ashydev.nightitems.item.types.OperatorPosition;
import me.ashydev.nightitems.item.types.SimpleContainer;
import me.ashydev.nightitems.util.Color;
import me.ashydev.nightitems.util.NumberFormatter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class AbstractItemStat<T> implements ItemStat<T> {
    private final SimpleContainer<T> container;
    private final String name;
    private final String[] description;
    private final Color color;
    @Getter
    private final String operator;
    private final OperatorPosition position;

    public AbstractItemStat(String name, Color color, String operator, OperatorPosition position, String... description) {
        this.name = name;
        this.color = color;
        this.operator = operator;
        this.position = position;
        this.description = description;

        container = new SimpleContainer<>(null);
    }

    public AbstractItemStat(T value, String name, Color color, String operator, OperatorPosition position, String... description) {
        this.name = name;
        this.color = color;
        this.operator = operator;
        this.position = position;
        this.description = description;

        container = new SimpleContainer<>(value);
    }

    @Override
    public OperatorPosition getOperatorPosition() {
        return position;
    }

    @Override
    public T get() {
        return container.get();
    }

    @Override
    public void set(T value) {
        container.set(value);
    }

    @Override
    public String[] description() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String convert() {
        StringBuilder str = new StringBuilder();
        str.append("&7").append(getName()).append(": ").append(getColor().getHex("&#"));

        if (getOperatorPosition() == OperatorPosition.START)
            str.append(getOperator());

        if (container.isNull()) {
            return str.append("null").toString();
        }

        if (!(get() instanceof Number)) str.append(container.convert());
        else str.append(NumberFormatter.format(((Number) get()).doubleValue(), 999_999_999));

        if (getOperatorPosition() == OperatorPosition.END)
            str.append(getOperator());

        return str.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(@NotNull ItemStat<?> o) {
        return ((get() instanceof Comparable) && (o.get() instanceof Comparable)) ? ((Comparable<T>) get()).compareTo((T) o.get()) : 0;
    }

}
