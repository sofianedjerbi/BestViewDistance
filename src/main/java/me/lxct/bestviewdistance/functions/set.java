package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.gen.genPlayerData;
import static me.lxct.bestviewdistance.functions.get.*;

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

    private static void setPlayerLimits(Player player){
        if(getPlayerViewDistance(player) > getMaxViewDistance()){
            setPlayerViewDistance(player, getMaxViewDistance());
        }
        else if(getPlayerViewDistance(player) < getMinViewDistance()){
            setPlayerViewDistance(player, getMinViewDistance());
        }
    }

    public static void setServerLimits(){
        if(getActualReductionIndice() > 0.75){
            setServerReductionIndice(0.75);
        }
        else if(getActualReductionIndice() < 0){
            setServerReductionIndice(0);
        }
    }


    public static void setPlayersBestViewDistance(double ReductionIndice){
        for(Player player : Bukkit.getOnlinePlayers()) { // For each player...
            // Create player.yml
            genPlayerData(player);
            if(player.spigot().getPing() < 75 && getPlayerViewDistance(player) < getMaxViewDistance()){
                setPlayerViewDistance(player, getPlayerViewDistance(player)+1);
            }
            else if(player.spigot().getPing() > 999 && getPlayerViewDistance(player) > getMinViewDistance()){
                setPlayerViewDistance(player, getPlayerViewDistance(player)-1);
            }
            setPlayerLimits(player);
            player.setViewDistance((int) Math.round((getPlayerViewDistance(player))*(1-ReductionIndice)));
        }
    }
}