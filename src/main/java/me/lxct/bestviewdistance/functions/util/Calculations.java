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
        final double reductionIndice = timings.getReductionIndice();
        for (final Player p : Bukkit.getOnlinePlayers()) {
            final BVDPlayer player = onlinePlayers.get(p);

            int supportedViewDistance = player.getSupportedViewDistance(); // View distance supported by player

            if (usePing) {
                final int ping = player.getPing(); // Get ping

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

    // A FUNCTION THAT SET THE VIEW DISTANCE WITH FUNCTION THAT BREAK ASYNC CHAINS
    public static void applyViewDistance() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            final BVDPlayer player = onlinePlayers.get(p);
            if (player.isAfk()
                    && player.getCurrentViewDistance() != afk
                    && useAFKView) { // AFK VIEW
                player.setViewDistance(afk);
            } else if (player.isFlying()
                    && player.getCurrentViewDistance() != onFlyingView
                    && useOnFlyingView) { // FLYING VIEW
                player.setViewDistance(onFlyingView);
            } else if (!player.isWaitingForTpUnset()) {
                final int vdistToApply = player.getViewBypass(player.getScheduledViewDistance());
                if (vdistToApply != player.getCurrentViewDistance()) {
                    player.setViewDistance(vdistToApply);
                }
            }
        }
    }
}
