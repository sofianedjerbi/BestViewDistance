package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.get.getMinViewDistance;

public class gen extends org.bukkit.plugin.java.JavaPlugin {

    public static void genFolders(){
        new File("plugins/BestViewDistance").mkdirs();
        new File("plugins/BestViewDistance/data").mkdirs();
    }


    public static void genConfig() {
        File data = new File("plugins/BestViewDistance/config.yml");
        FileConfiguration initconfig = YamlConfiguration.loadConfiguration(data);
        if (!data.exists()) {
            try {
                data.createNewFile();
                initconfig.set("ViewDistance.Min", 4);
                initconfig.set("ViewDistance.Max", 16);
                initconfig.set("ViewDistance.Delay", 20);
                initconfig.save(data);
            } catch (IOException ex) {
                Bukkit.getLogger().info("[BestViewDistance] Cannot create config.yml file. Please make sure you have editing rights on the entire plugin folder.");
            }
        }
    }

    static void genPlayerData(Player player) {
        File file = new File("plugins/BestViewDistance/data/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
                config.set("ViewDistance", getMinViewDistance());
                config.save(file);
            } catch (IOException ex) {
                Bukkit.getLogger().info("[BestViewDistance] Cannot create playerdatas yml files. Please make sure you have editing rights on the entire plugin folder.");
            }
        }
    }

    public static void genServerData() {
        File file = new File("plugins/BestViewDistance/data/server.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
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