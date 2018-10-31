package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.get.getActualReductionIndice;
import static me.lxct.bestviewdistance.functions.get.getPlayerViewDistance;
import static me.lxct.bestviewdistance.functions.variable.afkList;

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
        int playerViewDistance = getPlayerViewDistance(player);
        if(playerViewDistance > variable.max){
            setPlayerViewDistance(player, variable.max);
        }
        else if(playerViewDistance < variable.min){
            setPlayerViewDistance(player, variable.min);
        }
        if(player.getViewDistance() > variable.max){
            player.setViewDistance(variable.max);
        }
        else if(player.getViewDistance() < variable.min) {
            player.setViewDistance(variable.min);
        }


    }

    public static void setServerLimits(){
        double ReductionIndice = getActualReductionIndice();
        if(ReductionIndice > variable.maxindice){
            setServerReductionIndice(variable.maxindice);
        }
        else if(ReductionIndice < 0){
            setServerReductionIndice(0);
        }
    }


    public static void setPlayersBestViewDistance(double ReductionIndice){
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(afkList.contains(player)) {
                player.setViewDistance(variable.min);
            }
            else{
                int viewDistance = getPlayerViewDistance(player);
                if (player.spigot().getPing() < variable.aping) {
                    viewDistance = viewDistance + 1;
                    setPlayerViewDistance(player, viewDistance);
                } else if (player.spigot().getPing() >= variable.rping) {
                    viewDistance = viewDistance - 1;
                    setPlayerViewDistance(player, viewDistance);
                }
                player.setViewDistance((int) (viewDistance * (1 - ReductionIndice)));
                setPlayerLimits(player);
            }
        }
    }
}