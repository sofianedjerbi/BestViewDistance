package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
            if(Variable.playerSettingsViewDistance.containsKey(player.getName())){
                return Variable.playerSettingsViewDistance.get(player.getName());
            }
            else{
                return Variable.min;
            }
        } else {
            return player.getViewDistance();
        }
    }
}