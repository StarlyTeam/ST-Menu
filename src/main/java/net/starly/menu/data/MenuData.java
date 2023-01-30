package net.starly.menu.data;

import net.starly.core.data.Config;
import net.starly.menu.MenuMain;
import org.bukkit.Bukkit;

public class MenuData {

    private final String name;
    private final Config config;

    public MenuData(String name) {
        this.name = name;
        this.config = new Config("menu/" + name, MenuMain.getPlugin());
    }

    public void create() {
        config.setInventory(name, Bukkit.createInventory(null, 54, name), name);
        config.saveConfig();
    }

    public void delete() {
        config.delete();
    }

    public Boolean isExist() {
        return config.isFileExist();
    }
}
