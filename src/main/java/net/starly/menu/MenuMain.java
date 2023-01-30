package net.starly.menu;

import net.starly.core.bstats.Metrics;
import net.starly.core.data.Config;
import net.starly.menu.command.MenuCmd;
import net.starly.menu.command.tabcomplete.MenuTab;
import net.starly.menu.event.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class MenuMain extends JavaPlugin {
    private static JavaPlugin plugin;
    private final static Logger log = Bukkit.getLogger();
    public static Config config;

    @Override
    public void onEnable() {
        init();
    }

    public void init() {
        // DEPENDENCIES
        if (Bukkit.getPluginManager().getPlugin("ST-Core") == null) {
            log.warning("[" + plugin.getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            log.warning("[" + plugin.getName() + "] 다운로드 링크 : &fhttps://starly.kr/discord");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        plugin = this;
        new Metrics(this, 17551);

        // CONFIG
        config = new Config("config", this);
        config.loadDefaultConfig();
        config.setPrefix("messages.prefix");
        new Config("example-menu", this).loadDefaultConfig();

        // COMMAND
        Bukkit.getPluginCommand("menu").setExecutor(new MenuCmd());
        Bukkit.getPluginCommand("menu").setTabCompleter(new MenuTab());

        // EVENT
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDragListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSwapHandItemsListener(), this);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
