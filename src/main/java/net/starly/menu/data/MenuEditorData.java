package net.starly.menu.data;

import net.starly.core.data.Config;
import net.starly.core.util.PreCondition;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;

public class MenuEditorData {

    public Inventory getInventory(Config config, String path) {

        PreCondition.nonNull(path, "path는 null일 수 없습니다.");
        ConfigurationSection section = config.getConfig().getConfigurationSection(path);
        Inventory inventory;
        try {
            inventory = Bukkit.createInventory(null, section.getInt("size"), section.getString("title"));
        } catch (Exception e) {
            throw new IllegalArgumentException("인벤토리를 불러오는데 실패했습니다. 경로: " + path);
        }

        try {
            section.getConfigurationSection("items").getKeys(false).forEach(key -> {
                for(int i : config.getConfig().getIntegerList(path + ".items." + key + ".slot")) {
                    inventory.setItem(i, config.getItemStack(path + ".items." + key));
                }
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("인벤토리를 불러오는데 실패했습니다. 경로: " + path + ".items");
        }
        return inventory;
    }
}