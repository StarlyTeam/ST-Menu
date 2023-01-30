package net.starly.menu.event;

import net.starly.menu.utils.RunCommandUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import static net.starly.menu.MenuMain.config;

public class PlayerSwapHandItemsListener implements Listener {
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();

        if(!player.hasPermission("starly.menu.shiftf")) return;

        if (player.isSneaking()) {
            if (config.getBoolean("others.shiftF.enable")) {
                RunCommandUtil.runCommand(player, config.getString("others.shiftF.open_settings"));
                event.setCancelled(true);
            }
        }
    }
}
