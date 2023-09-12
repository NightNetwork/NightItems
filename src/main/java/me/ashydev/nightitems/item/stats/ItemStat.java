package me.ashydev.nightitems.item.stats;

import me.ashydev.nightitems.item.component.DescribableComponent;
import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.types.*;
import me.ashydev.nightitems.util.Color;

import java.util.List;

public interface ItemStat<T> extends DescribableComponent, Nameable, Container<T>, Comparable<ItemStat<?>>, Convertible<String>, Colorable {

    @Override
    default List<String> draw(List<String> input) {
        input.add(convert());
        return input;
    }

    String getOperator();
    OperatorPosition getOperatorPosition();

    default boolean hasOperator() {
        return getOperator() != null;
    }

    default boolean isOperator(String operator) {
        return hasOperator() && getOperator().equals(operator);
    }
}
