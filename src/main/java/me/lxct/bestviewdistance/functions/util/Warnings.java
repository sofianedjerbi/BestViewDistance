package me.lxct.bestviewdistance.functions.util;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Bukkit;

import static me.lxct.bestviewdistance.commands.Commands.colorize;
import static me.lxct.bestviewdistance.functions.data.Variable.serverVersion;

public class Warnings {

    public static void checkProtocolLib() {
        if (!serverVersion.contains("1.13") && !serverVersion.contains("1.14")) {
            if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
                Bukkit.getConsoleSender().sendMessage(serverVersion);
                Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&l PROTOCOLLIB IS REQUIRED TO RUN THIS PLUGIN BELOW 1.13.X!"));
                Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lPlease download ProtocolLib."));
                Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lLink for ProtocolLib: https://www.spigotmc.org/resources/1997/"));
                Bukkit.getPluginManager().disablePlugin(BestViewDistance.plugin);
            }
        }
    }

    public static void checkServerView() {
        Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] Make sure that view-distance setting is set on \"3\" in every .yml files."));
    }
}