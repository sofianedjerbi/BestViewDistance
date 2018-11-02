package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.get.getPlayerViewDistance;
import static me.lxct.bestviewdistance.functions.variable.playerViewDistance;

public class gen extends org.bukkit.plugin.java.JavaPlugin {

    public static void genFolders(){
        new File("plugins/BestViewDistance").mkdirs();
        new File("plugins/BestViewDistance/data").mkdirs();
    }

    public static void genPlayerData(OfflinePlayer player) {
        File file = new File("plugins/BestViewDistance/data/" + player.getUniqueId() + ".yml");
        if (!file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            try {
                file.createNewFile();
                config.set("ViewDistance", variable.min);
                config.save(file);
            } catch (IOException ex) {
                Bukkit.getLogger().info("[BestViewDistance] Cannot create playerdatas yml files. Please make sure you have editing rights on the entire plugin folder.");
            }
        }
    }

    public static void genAllOnlinePlayerData() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            genPlayerData(player);
            playerViewDistance.put(player.getUniqueId(), getPlayerViewDistance(player)); // LOAD PLAYER DATA
        }
    }

    public static void genServerData() {
        File file = new File("plugins/BestViewDistance/data/server.yml");
        if (!file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            try {
                file.createNewFile();
                config.set("ReductionIndice", 0.0);
                config.save(file);
            } catch (IOException ex) {
                Bukkit.getLogger().info("[BestViewDistance] Cannot create server.yml file. Please make sure you have editing rights on the entire plugin folder.");
            }
        }

    }
}