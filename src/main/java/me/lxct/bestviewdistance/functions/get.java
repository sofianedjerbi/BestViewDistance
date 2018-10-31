package me.lxct.bestviewdistance.functions;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class get extends org.bukkit.plugin.java.JavaPlugin {

    public static int getPlayerViewDistance(Player player){
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/BestViewDistance/data/" + player.getUniqueId() + ".yml"));
        return config.getInt("ViewDistance");
    }

    public static double getActualReductionIndice(){
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/BestViewDistance/data/server.yml"));
        return config.getDouble("ReductionIndice");
    }

    public static double getNewReductionIndice(Double TPS){
        double ActualReductionIndice = getActualReductionIndice();
        if(TPS > variable.tpslimit && TPS < 20){ // Min Indice = 0
            return ActualReductionIndice- variable.tpschange;
        }
        else if(TPS < variable.tpslimit) { // Max Indice = 0.75 (Pay Attention. 75% of 12 View Distance = 3 Chunks.)
            return ActualReductionIndice+ variable.tpschange;
        }
        else {
            return ActualReductionIndice;
        }
    }

}