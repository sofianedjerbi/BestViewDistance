package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.Variable.*;

public class Other {
    private static YamlConfiguration customConfig;

    public static void putPlayerAFK() { // What this function does ? if the player has exactly the same position as two minutes ago, he'll be set in "AFK" mode.
        for (Player player : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            Location location = player.getLocation(); // Get Location
            if (location.equals(playerLocation.get(player.getName()))){ // If same position ...
                if(!afkList.contains(player.getName())) { // If player is not afk
                    afkList.add(player.getName()); // SET AFK
                }
            } else { // If it's not the same position...
                playerLocation.put(player.getName(), player.getLocation()); // Actualize the position.
            }
        }
    }

    public static void loadMessagesYml() { // Load messages.yml
        File customConfigFile = new File("messages.yml");
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    static FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public static void genMessagesYml() { // Generate messages.yml file
        Bukkit.getLogger().info("[BestViewDistance] Generating messages.yml...");
        BestViewDistance.plugin.saveResource("messages.yml", false);
        Bukkit.getLogger().info("[BestViewDistance] messages.yml generated !");
    }
    public static String replacePlaceHolders(String string){ // Replace placeholders in messages.yml
        string = string.replace("%TPS%", String.valueOf(Bukkit.getServer().getTPS()[0]));
        string = string.replace("%PLAYER%", player.getName());
        string = string.replace("%VIEWDISTANCE%", String.valueOf(player.getViewDistance()));
        string = string.replace("%SETTINGS%", String.valueOf(Get.getViewDistance(player)));
        string = string.replace("%REDUCTIONINDICE%", String.valueOf(Math.round(reductionIndice*100)));
        string = string.replace("%PING%", String.valueOf(player.spigot().getPing()));
        string = string.replace("%PINGVIEW%", String.valueOf(playerViewDistance.get(player.getName())));
        return string;
    }
}