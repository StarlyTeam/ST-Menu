package net.starly.menu.event;

import net.starly.menu.data.MenuMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (MenuMap.menuMap.containsKey(player)) MenuMap.menuMap.remove(player);
    }
}
