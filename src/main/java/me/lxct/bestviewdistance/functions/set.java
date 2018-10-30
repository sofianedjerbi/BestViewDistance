package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.gen.getMaxViewDistance;
import static me.lxct.bestviewdistance.functions.gen.getPlayerViewDistance;

public class set extends org.bukkit.plugin.java.JavaPlugin {

    private static void setPlayerViewDistance(Player player, int x){
        File file = new File("plugins/BestViewDistance/data/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("ViewDistance", x);
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().info("[BestViewDistance] Cannot edit yml files. Please make sure you have editing rights on the entire plugin folder.");
        }
    }


    public static void setServerReductionIndice(double x){
        File file = new File("plugins/BestViewDistance/data/server.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("ReductionIndice", x);
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().info("[BestViewDistance] Cannot edit yml files. Please make sure you have editing rights on the entire plugin folder.");
        }
    }

    public static void setPlayersBestViewDistance(double ReductionIndice){
        for(Player player : Bukkit.getOnlinePlayers()) { // For each player...
            if(player.spigot().getPing() < 50 && getPlayerViewDistance(player) < getMaxViewDistance()){
                int ViewDistance = (int) Math.round(getPlayerViewDistance(player)*(1-ReductionIndice));
                player.setViewDistance(ViewDistance);
                setPlayerViewDistance(player, ViewDistance);
            }

        }
    }
}