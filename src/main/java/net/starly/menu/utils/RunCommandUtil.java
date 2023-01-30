package net.starly.menu.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class RunCommandUtil {
    public static void runCommand(Player player, String line) {
        if (line.contains("[player]")) {
            player.performCommand(line.replace("[player] ", ""));
        } else if (line.contains("[console]")) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), line.replace("[console] ", "")
                    .replace("%player%", player.getDisplayName())
                    .replace("{player}", player.getDisplayName())
                    .replace("%player_name%", player.getDisplayName())
                    .replace("{player_name}", player.getDisplayName()));
        } else if (line.contains("[sound]")) {
            String sound = line.replace("[sound] ", "");
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1, 1);
        } else if (line.contains("[close]")) {
            player.closeInventory();
        }
    }
}
