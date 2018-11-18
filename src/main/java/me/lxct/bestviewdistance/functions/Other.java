package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static me.lxct.bestviewdistance.functions.Get.getPlayerConfig;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Other {
    private static FileConfiguration customConfig;

    public static void putPlayerAFK() { // What this function does ? if the player has exactly the same position as two minutes ago, he'll be set in "AFK" mode.
        for (Player player : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            Location location = player.getLocation(); // Get Location
            if (location.equals(playerLocation.get(player.getName()))) { // If same position ...
                if (!afkList.contains(player.getName())) { // If player is not afk
                    afkList.add(player.getName()); // SET AFK
                }
            } else { // If it's not the same position...
                playerLocation.put(player.getName(), player.getLocation()); // Actualize the position.
            }
        }
    }

    static void genServerCustomViewFile(int view) { // Create a "player data" file and set a view distance.
        File serverFile = new File(BestViewDistance.plugin.getDataFolder() + "/data/", "server.yml");
        serverFile.mkdirs();
        try {
            YamlConfiguration serverConfig = YamlConfiguration.loadConfiguration(serverFile);
            serverConfig.set("Data.CustomViewDistance", view);
            serverConfig.save(serverFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveServerCustomViewFile() { // Create a "player data" file.
        File serverFile = new File(BestViewDistance.plugin.getDataFolder() + "/data/", "server.yml");
        try {
            YamlConfiguration serverConfig = YamlConfiguration.loadConfiguration(serverFile);
            serverConfig.save(serverFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void genPlayerCustomViewFile(Player player, int view) { // Create a "player data" file and set a view distance.
        File userFile = new File(BestViewDistance.plugin.getDataFolder() + "/data/", player.getName() + ".yml");
        userFile.mkdirs();
        try {
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
            userConfig.set("Data.CustomViewDistance", view);
            getPlayerConfig(player).set("Data.CustomViewDistanceIsSet", ""); // Set true
            userConfig.save(userFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkFile(File directory, String name) { // List files of a directory and see if it contain the file named "name"
        for (File eachFiles : Objects.requireNonNull(directory.listFiles())) {
            if (name.equals(eachFiles.toString())) {
                return true;
            }
        }
        return false;
    }

    static void savePlayerCustomViewFile(Player player) { // Create a "player data" file.
        File userFile = new File(BestViewDistance.plugin.getDataFolder() + "/data/", player.getName() + ".yml");
        try {
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
            userConfig.save(userFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void genOnlinePlayerData() { // Set all playerLiveViewDistance to onLoginView.
        playerLiveViewDistance.clear();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            playerLiveViewDistance.put(player.getName(), Variable.onloginview);
        }
    }

    public static void loadMessagesYml() { // Load messages.yml
        File customConfigFile = new File(BestViewDistance.plugin.getDataFolder(), "messages.yml");
        try {
            customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
            try {
                customConfig.load(customConfigFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            customConfig = new YamlConfiguration();
        }
    }

    public static FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public static void genMessagesYml() { // Generate messages.yml file
        if (!new File(BestViewDistance.plugin.getDataFolder(), "messages.yml").exists()) {
            Bukkit.getLogger().info("[BestViewDistance] Generating messages.yml...");
            BestViewDistance.plugin.saveResource("messages.yml", false);
            Bukkit.getLogger().info("[BestViewDistance] messages.yml generated !");
        }
    }

    public static String replacePlaceHolders(String string) { // Replace placeholders in messages.yml
        if (string.contains("%TPS%")) {
            string = string.replace("%TPS%", String.valueOf(Bukkit.getServer().getTPS()[0]));
        }
        if (string.contains("%PLAYER%")) {
            string = string.replace("%PLAYER%", playerName);
        }
        if (string.contains("%VIEWDISTANCE%")) {
            string = string.replace("%VIEWDISTANCE%", String.valueOf(player.getViewDistance()));
        }
        if (string.contains("%SETTINGS%")) {
            string = string.replace("%SETTINGS%", String.valueOf(Get.getViewDistance(player)));
        }
        if (string.contains("%REDUCTIONINDICE%")) {
            string = string.replace("%REDUCTIONINDICE%", String.valueOf(Math.round(reductionIndice * 100)));
        }
        if (string.contains("%PING%")) {
            string = string.replace("%PING%", String.valueOf(player.spigot().getPing()));
        }
        if (string.contains("%PINGVIEW%")) {
            string = string.replace("%PINGVIEW%", String.valueOf(playerViewDistance.get(player.getName())));
        }
        return string;
    }
}