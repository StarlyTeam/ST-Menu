package net.starly.menu.event;

import net.starly.core.data.Config;
import net.starly.menu.MenuMain;
import net.starly.menu.data.MenuMap;
import net.starly.menu.utils.RunCommandUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) return;

        if (MenuMap.menuMap.containsKey(player)) {
            event.setCancelled(true);
            Config config = new Config("menu/" + MenuMap.menuMap.get(player), MenuMain.getPlugin());
            ConfigurationSection section = config.getConfig().getConfigurationSection(MenuMap.menuMap.get(player));


            section.getConfigurationSection("items").getKeys(false).forEach(key -> {

                for (int i : config.getConfig().getIntegerList(MenuMap.menuMap.get(player) + ".items." + key + ".slot")) {

                    if (event.getSlot() == i) {

                        if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.SHIFT_LEFT) {
                            // 좌클릭 커맨드
                            for (String leftClickCommands : config.getStringList(MenuMap.menuMap.get(player) + ".items." + key + ".left_click_commands")) {
                                RunCommandUtil.runCommand(player, leftClickCommands);
                            }

                        } else if (event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.SHIFT_RIGHT) {
                            // 우클릭 커맨드
                            for (String rightClickCommands : config.getStringList(MenuMap.menuMap.get(player) + ".items." + key + ".right_click_commands")) {
                                RunCommandUtil.runCommand(player, rightClickCommands);
                            }
                        }
                    }
                }
            });
        }
    }
}
