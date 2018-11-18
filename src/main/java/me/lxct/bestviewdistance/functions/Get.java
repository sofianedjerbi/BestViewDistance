package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Get {
    // CALCULATE NEW REDUCTION INDICE
    public static double getNewReductionIndice(Double TPS) {
        if (TPS > Variable.tpslimit && TPS < 20) { // If tps > tps limit
            return Variable.reductionIndice - Variable.tpschange; // Decrease indice
        } else if (TPS < Variable.tpslimit) { // If tps < tps limit
            return Variable.reductionIndice + Variable.tpschange; // Increase indice
        } else {
            return Variable.reductionIndice; // Nothing.
        }
    }

    static int getViewDistance(Player player) {
        if (Bukkit.getVersion().contains("1.12")) {
            if (Variable.playerSettingsViewDistance.containsKey(player.getName())) {
                return Variable.playerSettingsViewDistance.get(player.getName());
            } else {
                return Variable.min;
            }
        } else {
            //noinspection deprecation
            return player.getClientViewDistance();
        }
    }

    public static YamlConfiguration getPlayerConfig(Player player) { // Return the "player data" file.
        return YamlConfiguration.loadConfiguration(new File(BestViewDistance.plugin.getDataFolder() + "/data/", player.getName() + ".yml"));
    }

    public static YamlConfiguration getServerConfig() { // Return the "server data" file.
        return YamlConfiguration.loadConfiguration(new File(BestViewDistance.plugin.getDataFolder() + "/data/", "server.yml"));
    }

    public static boolean getServerCustomViewBoolean() {
        return getServerConfig().getBoolean("Data.CustomViewDistanceIsSet");
    }

    public static int getServerCustomViewDistance() {
        return getServerConfig().getInt("Data.CustomViewDistance");
    }

    public static int getPlayerCustomViewDistance(Player player) {
        return getPlayerConfig(player).getInt("Data.CustomViewDistance");
    }

    public static boolean getPlayerCustomViewBoolean(Player player) {
        return getPlayerConfig(player).getBoolean("Data.CustomViewDistanceIsSet");
    }

}