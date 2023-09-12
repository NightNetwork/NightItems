package me.ashydev.nightitems.item;

import lombok.Getter;
import me.ashydev.nightitems.item.component.ActionComponent;
import me.ashydev.nightitems.item.component.ItemComponent;
import me.ashydev.nightitems.item.types.Drawable;
import me.ashydev.nightitems.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

@Getter
public abstract class AbstractNightItem implements NightItem {
    protected String name;
    protected Material material;
    protected List<ItemComponent> components;
    protected List<Pair<ItemComponent, Double>> prioritizedComponents;
    protected PriorityQueue<Pair<Drawable<List<String>>, Double>> drawables = new PriorityQueue<>((o1, o2) -> o2.getSecond().compareTo(o1.getSecond()));

    public AbstractNightItem(String name, Material material, List<ItemComponent> components) {
        this.name = name;
        this.material = material;
        this.components = components;
    }

    public AbstractNightItem(String name, Material material, ItemComponent... components) {
        this.name = name;
        this.material = material;
        this.components = Arrays.asList(components);
    }

    public AbstractNightItem(String name, Material material, List<Pair<ItemComponent, Double>> components, boolean prioritized) {
        this.name = name;
        this.material = material;
        this.components = components.stream().map(Pair::getFirst).toList();
        this.prioritizedComponents = components;
    }

    @SafeVarargs
    public AbstractNightItem(String name, Material material, Pair<ItemComponent, Double>... components) {
        this.name = name;
        this.material = material;

        this.components = Arrays.stream(components).map(Pair::getFirst).toList();
        this.prioritizedComponents = Arrays.asList(components);
    }

    public void setupDrawables() {
        drawables.clear();

        for (ItemComponent component : components) {
            if (component instanceof Drawable) {

                Drawable<List<String>> drawable = (Drawable<List<String>>) component;


                if (prioritizedComponents != null) {
                    List<ItemComponent> priority = prioritizedComponents.stream().map(Pair::getFirst).toList();

                    if (!priority.contains(component)) {
                        drawables.add(new Pair<>(drawable, 0d));
                    } else {
                        drawables.add(new Pair<>(drawable, prioritizedComponents.stream().filter(pair -> pair.getFirst().equals(component)).findFirst().get().getSecond()));
                    }
                }
            }
        }
    }

    public void registerAction(ActionComponent component, Plugin plugin) {
        components.add(component);

        Bukkit.getPluginManager().registerEvents(component, plugin);
    }


    public List<ItemComponent> getComponents() {
        return List.copyOf(components);
    }
}
