package me.ashydev.nightitems.item.component;

import me.ashydev.nightitems.item.types.ChildContainer;

public interface TreeComponent<T extends TreeComponent<?>> extends ItemComponent, ChildContainer<T> { }
