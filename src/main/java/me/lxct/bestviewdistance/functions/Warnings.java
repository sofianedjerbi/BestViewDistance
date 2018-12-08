package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;

import static me.lxct.bestviewdistance.commands.Commands.colorize;

public class Warnings {

    public static void checkProtocolLib() {
        if (Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.11") || Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.9") || Bukkit.getVersion().contains("1.8")) {
            if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
                Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lYOU NEED PROTOCOLLIB TO RUN THIS PLUGIN!"));
                Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lPlease download ProtocolLib."));
                Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lLink for ProtocolLib: https://www.spigotmc.org/resources/1997/"));
            }
        }
    }

    public static void checkServerView() {
        int spigotView = Bukkit.spigot().getSpigotConfig().getInt("world-settings.default.view-distance");
        if (spigotView > 4) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lView distance setting inside spigot.yml is \"" + spigotView + "\"."));
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lSetting a view-distance over 4 will increase lags."));
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lThe plugin will override the view-distance. Don't worry about that."));
        }
    }
}