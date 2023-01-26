package net.starly.menu.command;

import net.starly.core.data.Config;
import net.starly.menu.MenuMain;
import net.starly.menu.data.MenuData;
import net.starly.menu.data.MenuEditorData;
import net.starly.menu.data.MenuMap;
import net.starly.menu.utils.RunCommandUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.starly.menu.MenuMain.config;


public class MenuCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission("starly.menu.help")) {
                player.sendMessage(config.getMessage("errorMessages.noPermission"));
                return true;
            }

            config.getMessages("messages.menu.main").forEach(player::sendMessage);
            return true;
        }

        switch (args[0]) {

            case "도움말": {
                if (!player.hasPermission("starly.menu.help")) {
                    player.sendMessage(config.getMessage("errorMessages.noPermission"));
                    return true;
                }

                if (player.isOp()) {
                    config.getMessages("messages.menu.help").forEach(player::sendMessage);
                    return true;
                } else {
                    config.getMessages("messages.menu.main").forEach(player::sendMessage);
                    return true;
                }
            }

            case "생성": {

                if (args.length == 1) {
                    player.sendMessage(config.getMessage("errorMessages.menu.noName"));
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                    return true;
                }

                if (!player.hasPermission("starly.menu.create")) {
                    player.sendMessage(config.getMessage("errorMessages.noPermission"));
                    return true;
                }

                String menuName = args[1];
                MenuData menu = new MenuData(menuName);

                if (menuName.replaceAll("[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]", "").length() != 0) {
                    player.sendMessage(config.getMessage("errorMessages.menu.invalidName"));
                    return true;
                }

                if (menu.isExist()) {
                    player.sendMessage(config.getMessage("errorMessages.menu.alreadyExist"));
                    return true;
                }

                menu.create();
                player.sendMessage(config.getMessage("messages.menu.create").replace("{name}", menuName));
                return true;
            }

            case "제거": {

                if (args.length == 1) {
                    player.sendMessage(config.getMessage("errorMessages.menu.noName"));
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                    return true;
                }

                String menuName = args[1];
                MenuData menu = new MenuData(menuName);

                if (!player.hasPermission("starly.menu.remove")) {
                    player.sendMessage(config.getMessage("errorMessages.noPermission"));
                    return true;
                }

                if (!menu.isExist()) {
                    player.sendMessage(config.getMessage("errorMessages.menu.notExist"));
                    return true;
                }

                menu.delete();
                player.sendMessage(config.getMessage("messages.menu.remove").replace("{name}", menuName));
                return true;
            }

            case "열기": {

                if (args.length == 1) {
                    player.sendMessage(config.getMessage("errorMessages.menu.noName"));
                    return true;
                }

                if (args.length != 2) {
                    player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                    return true;
                }

                if (!player.hasPermission("starly.menu.open")) {
                    player.sendMessage(config.getMessage("errorMessages.noPermission"));
                    return true;
                }

                String menuName = args[1];
                player.openInventory(new MenuEditorData().getInventory(new Config("menu/" + menuName, MenuMain.getPlugin()), menuName));
                new Config("menu/" + menuName, MenuMain.getPlugin()).getStringList(menuName + ".open_settings").forEach(command -> RunCommandUtil.runCommand(player, command));
                MenuMap.menuMap.put(player, menuName);
                return true;
            }

            case "목록": {
                player.sendMessage(String.join("\n", new Config("menu/", MenuMain.getPlugin()).getFileNames().toArray(new String[0])));
                return true;
            }

            case "리로드": {
                config.reloadConfig();
                player.sendMessage(config.getMessage("messages.menu.reloadConfig"));
                return true;
            }


            default: {
                player.sendMessage(config.getMessage("errorMessages.wrongCommand"));
                return true;
            }
        }
    }
}
