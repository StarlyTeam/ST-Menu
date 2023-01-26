package net.starly.menu.event;

import net.starly.menu.data.MenuMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class InventoryMoveListener implements Listener {
    @EventHandler
    public void onMove(InventoryMoveItemEvent event) {
        Player player = (Player) event.getInitiator().getHolder();
        if (MenuMap.menuMap.containsKey(player)) event.setCancelled(true);
    }
}
