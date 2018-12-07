package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class UpdateConfig {
    public static void updateConfig() {
        FileConfiguration config = BestViewDistance.plugin.getConfig();
        double ver = config.getDouble("Version");
        if (ver < 1.1) {
            Bukkit.getLogger().info("[BestViewDistance] Update config.yml...");
            config.set("Version", 1.1);
            config.set("ViewDistance.UnsetTeleportViewDelay", 5);
            config.set("ViewDistance.OnTeleport", 4);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"OnTeleport\" and \"UnsetTeleportViewDelay\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
        if (ver < 1.2) {
            Bukkit.getLogger().info("[BestViewDistance] Update config.yml...");
            config.set("Version", 1.2);
            config.set("Other.ReduceViewOnTeleport", true);
            BestViewDistance.plugin.saveConfig();
            Bukkit.getLogger().info("[BestViewDistance] Updated config.yml!");
            Bukkit.getLogger().info("[BestViewDistance] Added: \"ReduceViewOnTeleport\"");
            Bukkit.getLogger().info("[BestViewDistance] More information here: https://www.spigotmc.org/resources/61963/updates");
        }
    }
}
