package me.lxct.bestviewdistance.functions.util;

import me.lxct.bestviewdistance.functions.BVDPlayer;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Calculations {
    // THE MAIN FUNCTION ! CALCULATE BEST PLAYER VIEW DISTANCE WITH REDUCTION INDICE
    public static void calculatePlayersBestViewDistance() {
        timings.actualize();
        double reductionIndice = timings.getReductionIndice();
        for (Player p : Bukkit.getOnlinePlayers()) {
            BVDPlayer player = onlinePlayers.get(p);

            int supportedViewDistance = player.getSupportedViewDistance(); // View distance supported by player

            if(usePing) {
                int ping = player.getPing(); // Get ping

                if (ping < Variable.aping && ping >= safePing) { // If ping need to decrease vdist
                    ++supportedViewDistance; // increase
                } // Low ping = More View Distance
                else if (ping >= Variable.rping) { // If ping need to increase vdist
                    --supportedViewDistance; // Decrease
                } // Big ping = Less View Distance
            } else {
                supportedViewDistance = player.getCurrentMaxLimit();
            }
            
            player.setSupportedViewDistance(supportedViewDistance); // Store in var

            player.setScheduledViewDistance(Math.toIntExact(Math.round((supportedViewDistance + moreThanSettings) * (1 - reductionIndice))));
            // Apply percentage && Store result of calculations
        }
    }
}
