package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Messages {

    private FileConfiguration customConfig;


    public Messages() { // Load messages.yml

        if (!new File(BestViewDistance.plugin.getDataFolder(), "messages.yml").exists()) {
            Bukkit.getLogger().info("[BestViewDistance] Generating messages.yml...");
            BestViewDistance.plugin.saveResource("messages.yml", false);
            Bukkit.getLogger().info("[BestViewDistance] messages.yml generated !");
        }

        File customConfigFile = new File(BestViewDistance.plugin.getDataFolder(), "messages.yml");
        this.customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        try {
            this.customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration get() {
        return this.customConfig;
    }
}
