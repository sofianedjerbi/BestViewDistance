package me.lxct.bestviewdistance.functions.util;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class UpdateConfig {
    public static void updateConfig() {
        final FileConfiguration config = BestViewDistance.plugin.getConfig();
        final double ver = config.getDouble("Version");
        if (ver < 2.0) {
            final File f1 = new File("./plugins/BestViewDistance/config.yml");
            final File f2 = new File("./plugins/BestViewDistance/old-config.yml");
            final boolean b1 = f1.renameTo(f2);
            if (b1 && f1.exists()) {
                Bukkit.getLogger().info("[BestViewDistance] Changed config file.");
                Bukkit.getLogger().info("[BestViewDistance] Old \"config.yml\" will be renamed to \"old-config.yml\"!");
            } else {
                final boolean b2 = f1.delete();
                if (b2) {
                    Bukkit.getLogger().info("[BestViewDistance] Deleted old config file.");
                } else {
                    Bukkit.getLogger().info("[BestViewDistance] Failed to delete old config file. Please delete it by yourself.");
                }
            }
        }
        if (ver < 2.1) {
            Bukkit.getLogger().info("[BestViewDistance] Updating config.yml...");
            config.set("Version", 2.1);
            config.set("Features.UsePermissions", false);
            config.set("Misc.DecimalsTPS", 2);
            config.set("Misc.DecimalsIndice", 2);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"UsePermissions\"");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"DecimalsTPS\"");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"DecimalsIndice\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
        if(ver < 2.2) {
            Bukkit.getLogger().info("[BestViewDistance] Updating config.yml...");
            config.set("Version", 2.2);
            config.set("Features.UseLoginView", true);
            config.set("Permissions.BypassAFKView", true);
            config.set("Permissions.BypassFlyingView", true);
            config.set("Permissions.BypassTeleportView", true);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"UseLoginView\"");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"Bypass permissions\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
        if(ver < 2.3) {
            Bukkit.getLogger().info("[BestViewDistance] Updating config.yml...");
            config.set("Version", 2.3);
            config.set("Worlds.Example.Max", 32);
            config.set("Worlds.Example.Min", 16);
            config.set("Worlds.Example2.Max", 8);
            config.set("Worlds.Example2.Min", 3);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"Custom wold view\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
        if(ver < 2.4) {
            Bukkit.getLogger().info("[BestViewDistance] Updating config.yml...");
            config.set("Version", 2.4);
            config.set("Regions.Example.Max", 32);
            config.set("Regions.Example.Min", 16);
            config.set("Regions.Example2.Max", 8);
            config.set("Regions.Example2.Min", 3);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"Custom region view\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
        if(ver < 2.5) {
            Bukkit.getLogger().info("[BestViewDistance] Updating config.yml...");
            config.set("Version", 2.5);
            config.set("Features.UseTPS", true);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"UseTPS\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
    }
}
