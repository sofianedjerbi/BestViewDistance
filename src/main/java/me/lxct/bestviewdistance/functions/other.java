package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static me.lxct.bestviewdistance.functions.set.setPlayerViewDistance;
import static me.lxct.bestviewdistance.functions.variable.*;

public class other {
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

    public static void saveReductionIndice(double x) {
        File file = new File("plugins/BestViewDistance/data/server.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("ReductionIndice", x);
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().info("[BestViewDistance] Cannot edit yml files. Please make sure you have editing rights on the entire plugin folder.");
        }
    }


    public static void loadServerReductionIndice() {
        File file = new File("plugins/BestViewDistance/data/server.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        variable.reductionIndice = config.getDouble("ReductionIndice");
    }

    public static void savePlayerViewDistance() {
        for(Map.Entry<UUID, Integer> entry : playerViewDistance.entrySet()) {
            UUID name = entry.getKey();
            Integer viewDist = entry.getValue();
            setPlayerViewDistance(Bukkit.getOfflinePlayer(name), viewDist);
        }
    }
}