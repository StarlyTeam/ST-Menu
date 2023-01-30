package net.starly.menu.command.tabcomplete;

import net.starly.core.data.Config;
import net.starly.menu.MenuMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MenuTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (args.length == 1) {
            if (player.isOp()) return Arrays.asList("생성", "제거", "열기", "리로드", "도움말");
            else return Arrays.asList("열기", "도움말");
        }

        if (args.length == 2) {
            if (player.isOp()) {
                if (args[0].equals("생성")) return Arrays.asList("<메뉴 이름>");
                else if (args[0].equals("열기") || args[0].equals("제거"))
                    try {
                        return Arrays.asList(new Config("menu/", MenuMain.getPlugin()).getFileNames().toArray(new String[0]));
                    } catch (Exception ignored) {
                        return Collections.emptyList();
                    }
            }
        } else {
            if (args[0].equals("열기"))
                try {
                    return Arrays.asList(new Config("menu/", MenuMain.getPlugin()).getFileNames().toArray(new String[0]));
                } catch (Exception ignored) {
                    return Collections.emptyList();
                }
        }
        return Collections.emptyList();
    }
}

