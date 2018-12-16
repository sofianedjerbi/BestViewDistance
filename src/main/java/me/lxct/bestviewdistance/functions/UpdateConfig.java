package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class UpdateConfig {
    public static void updateConfig() {
        FileConfiguration config = BestViewDistance.plugin.getConfig();
        double ver = config.getDouble("Version");
        if (ver < 2.0) {
            File f1 = new File("./plugins/BestViewDistance/config.yml");
            File f2 = new File("./plugins/BestViewDistance/old-config.yml");
            boolean b1 = f1.renameTo(f2);
            if (b1 && f1.exists()) {
                Bukkit.getLogger().info("[BestViewDistance] Changed config file.");
                Bukkit.getLogger().info("[BestViewDistance] Old \"config.yml\" will be renamed to \"old-config.yml\"!");
            } else {
                boolean b2 = f1.delete();
                if (b2) {
                    Bukkit.getLogger().info("[BestViewDistance] Deleted old config file.");
                } else {
                    Bukkit.getLogger().info("[BestViewDistance] Failed to delete old config file. Please delete it by yourself.");
                }
            }
        }
        if (ver < 2.1) {
            Bukkit.getLogger().info("[BestViewDistance] Update config.yml...");
            config.set("Version", 2.1);
            config.set("Features.UsePermissions", false);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"UsePermissions\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
    }
}
