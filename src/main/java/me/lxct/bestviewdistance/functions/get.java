package me.lxct.bestviewdistance.functions;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;

public class get extends org.bukkit.plugin.java.JavaPlugin {
    public static int getMaxViewDistance(){
        File data = new File("plugins/BestViewDistance/config.yml");
        FileConfiguration initconfig = YamlConfiguration.loadConfiguration(data);
        return (int) initconfig.get("ViewDistance.Max");
    }

    public static int getMinViewDistance(){
        File data = new File("plugins/BestViewDistance/config.yml");
        FileConfiguration initconfig = YamlConfiguration.loadConfiguration(data);
        return (int) initconfig.get("ViewDistance.Min");
    }

    public static Long getDelayViewDistance(){
        File data = new File("plugins/BestViewDistance/config.yml");
        FileConfiguration initconfig = YamlConfiguration.loadConfiguration(data);
        return (long) (int) initconfig.get("ViewDistance.Delay");
    }

    public static int getPlayerViewDistance(Player player){
        File file = new File("plugins/BestViewDistance/data/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return (int) config.get("ViewDistance");
    }

    public static double getActualReductionIndice(){
        File file = new File("plugins/BestViewDistance/data/server.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return (double) config.get("ReductionIndice");
    }

    public static double getNewReductionIndice(Double TPS){
        double ActualReductionIndice = getActualReductionIndice();
        if(TPS > 19.5 && TPS < 20){ // Min Indice = 0
            return ActualReductionIndice-0.05;
        }
        else if(TPS < 19.5) { // Max Indice = 0.75 (Pay Attention. 75% of 12 View Distance = 3 Chunks.)
            return ActualReductionIndice+0.05;
        }
        else {
            return ActualReductionIndice;
        }
    }
}