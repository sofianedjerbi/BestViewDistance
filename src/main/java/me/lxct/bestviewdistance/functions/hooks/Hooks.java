package me.lxct.bestviewdistance.functions.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import static me.lxct.bestviewdistance.functions.hooks.ProtocolLibHook.protocolLibHook;

public class Hooks {
    public static void checkHooks(Plugin plugin) {

        // PROTOCOLLIB
        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null && Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.11") || Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.9") || Bukkit.getVersion().contains("1.8")) { // Add 1.12 Support for Client View Distance
            try {
                protocolLibHook(plugin);
                Bukkit.getLogger().info("[BestViewDistance] Successfully hooked into ProtocolLib!");
            } catch (NoClassDefFoundError ignored) {
            }
        }

        // PLACEHOLDERAPI
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            try {
                new PlaceholderAPIHook(plugin).hook();
                Bukkit.getLogger().info("[BestViewDistance] Successfully hooked into PlaceholderAPI!");
            } catch (NoClassDefFoundError ignored) {
            }
        }
    }
}
