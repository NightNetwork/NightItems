package me.ashydev.nightitems.basic;

import me.ashydev.nightitems.basic.info.ItemCategory;
import me.ashydev.nightitems.basic.info.ItemType;
import me.ashydev.nightitems.basic.info.Rarity;
import me.ashydev.nightitems.basic.stats.SimpleItemStat;
import me.ashydev.nightitems.item.component.ActionComponent;
import me.ashydev.nightitems.item.types.OperatorPosition;
import me.ashydev.nightitems.util.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class ExampleItem extends BasicNightItem {
    public ExampleItem() {
        super("Example Item", Material.STONE, List.of(new SimpleItemStat<>(500d, "Damage", new Color("#ed5858"), "+", OperatorPosition.START, "This is an example stat.")),
                ItemCategory.NONE, ItemType.BLOCK, Rarity.ADMIN, "This is an example item.");

        registerAction(new ExampleActionComponent(), null);

        setupDrawables();
    }

    public static class ExampleActionComponent implements ActionComponent {

        @EventHandler
        public void onPlayerInteractEvent(PlayerInteractEvent e) {
            e.getPlayer().sendMessage("You right clicked!");
        }

        @Override
        public String getAction() {
            return "RIGHT CLICK";
        }

        @Override
        public long getCoolDown() {
            return 0;
        }

        @Override
        public String[] description() {
            return new String[]{"This is an example action component."};
        }

        @Override
        public String getName() {
            return "Example Action Component";
        }
    }
}
