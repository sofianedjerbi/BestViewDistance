package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Get.getMaxWorldLimits;
import static me.lxct.bestviewdistance.functions.Get.getSettingsViewDistance;
import static me.lxct.bestviewdistance.functions.Limit.limitViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Calculations {
    // THE MAIN FUNCTION ! CALCULATE BEST PLAYER VIEW DISTANCE WITH REDUCTION INDICE
    public static void calculatePlayersBestViewDistance(double ReductionIndice) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int supportedViewDistance;

            if (playerViewDistance.get(player.getName()) != null) {
                supportedViewDistance = playerViewDistance.get(player.getName()); // View distance supported by player
            } else {
                supportedViewDistance = onLoginView;
            }

            if(usePing) {
                int ping = player.spigot().getPing(); // Ping of player
                if (ping < Variable.aping && ping >= safePing) {
                    ++supportedViewDistance; // increase
                } // Low ping = More View Distance
                else if (ping >= Variable.rping) {
                    --supportedViewDistance; // Decrease
                } // Big ping = Less View Distance
            } else {
                supportedViewDistance = getMaxWorldLimits(player.getWorld());
            }
            
            playerViewDistance.put(player.getName(), limitViewDistance(player, supportedViewDistance)); // Store in var

            int viewDistance = Math.round((int) (Math.min(getSettingsViewDistance(player), supportedViewDistance + moreThanSettings) * (1 - ReductionIndice))); // Apply percentage
            // About the line under this comment. We set player view distance only if view distance doesn't get over limits
            // And respect player settings
            playerLiveViewDistance.put(player.getName(), limitViewDistance(player, viewDistance)); // Store result of calculations
        }
    }
}
