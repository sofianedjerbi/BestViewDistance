package me.lxct.bestviewdistance.functions;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;

public class gen extends org.bukkit.plugin.java.JavaPlugin {
    static int getMaxViewDistance(){
        File data = new File("plugins/BestViewDistance/config.yml");
        FileConfiguration initconfig = YamlConfiguration.loadConfiguration(data);
        return (int) initconfig.get("ViewDistance.Max");
    }

    private static int getMinViewDistance(){
        File data = new File("plugins/BestViewDistance/config.yml");
        FileConfiguration initconfig = YamlConfiguration.loadConfiguration(data);
        return (int) initconfig.get("ViewDistance.Min");
    }

    public static int getDelayViewDistance(){
        File data = new File("plugins/BestViewDistance/config.yml");
        FileConfiguration initconfig = YamlConfiguration.loadConfiguration(data);
        return (int) initconfig.get("ViewDistance.Delay");
    }

    public static int getPlayerViewDistance(Player player){
        File file = new File("plugins/BestViewDistance/data/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return (int) config.get("ViewDistance");
    }

    private static double getActualReductionIndice(){
        File file = new File("plugins/BestViewDistance/data/server.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return (double) config.get("ReductionIndice");
    }

    public static double getNewReductionIndice(Double TPS){
        if(TPS > 19.7){
            return getActualReductionIndice()+0.05;
        }
        else {
            return getActualReductionIndice()-0.05;
        }
    }
}