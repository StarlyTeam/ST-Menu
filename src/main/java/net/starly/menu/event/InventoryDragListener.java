package net.starly.menu.event;

import net.starly.menu.data.MenuMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (MenuMap.menuMap.containsKey(player)) event.setCancelled(true);
    }
}
