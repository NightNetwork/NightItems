package me.ashydev.nightitems.item;

import lombok.Getter;
import lombok.Setter;
import me.ashydev.nightitems.item.component.ActionComponent;
import me.ashydev.nightitems.item.component.DrawableComponent;
import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.types.Drawable;
import me.ashydev.nightitems.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

@Getter
@Setter
public abstract class AbstractNightItem implements NightItem {
    protected String name;
    protected Material material;
    protected boolean glowing, unique;
    protected List<ItemComponent> components;
    private final List<DrawableComponent> drawables = new ArrayList<>();
    public AbstractNightItem(String name, Material material, boolean glowing, boolean unique, List<ItemComponent> components) {
        this.name = name;
        this.glowing = glowing;
        this.unique = unique;
        this.material = material;
        this.components = components;
    }

    public AbstractNightItem(String name, Material material, boolean glowing, boolean unique, ItemComponent... components) {
        this.name = name;
        this.glowing = glowing;
        this.unique = unique;
        this.material = material;
        this.components = Arrays.asList(components);
    }


    public void setupDrawables() {
        drawables.clear();

        components.stream().filter(component -> component instanceof DrawableComponent).forEach(component -> drawables.add((DrawableComponent) component));
    }

    public void registerAction(ActionComponent component, Plugin plugin) {
        components.add(component);

        Bukkit.getPluginManager().registerEvents(component, plugin);
    }


    public List<ItemComponent> getComponents() {
        return List.copyOf(components);
    }

    @Override
    public boolean glowing() {
        return glowing;
    }

    @Override
    public boolean unique() {
        return unique;
    }
}
