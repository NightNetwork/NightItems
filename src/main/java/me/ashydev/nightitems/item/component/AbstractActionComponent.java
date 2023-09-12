package me.ashydev.nightitems.item.component;

import lombok.Getter;

@Getter
public abstract class AbstractActionComponent implements ActionComponent{
    private final String name;
    private final String[] description;
    private final String action;
    private final long coolDown;

    public AbstractActionComponent(String name, String action, long coolDown, String... description) {
        this.name = name;
        this.description = description;
        this.action = action;
        this.coolDown = coolDown;
    }

    @Override
    public String[] description() {
        return description;
    }
}
