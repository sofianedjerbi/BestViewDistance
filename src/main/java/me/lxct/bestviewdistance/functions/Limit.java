package me.lxct.bestviewdistance.functions;

import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Get.getMaxWorldLimits;
import static me.lxct.bestviewdistance.functions.Get.getMinWorldLimits;
import static me.lxct.bestviewdistance.functions.Get.getSettingsViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Limit {

    // MAKE SURE CALCULATED VIEW DISTANCE ISN'T OVER LIMITS
    static int limitViewDistance(Player player, int viewDistance) {
        int max = getMaxWorldLimits(player.getWorld());
        int min = getMinWorldLimits(player.getWorld());
        if (viewDistance > max) {
            viewDistance = max;
        } else if (viewDistance < min) {
            viewDistance = min;
        }
        return viewDistance;
    }

    // A FUNCTION FOR CLIENT SIDE SETTING. DON'T GIVE MORE VIEW DISTANCE THAN REQUIRED.
    static int limitClientSetting(Player player, int viewDistance) {
        int clientSideViewDistance = getSettingsViewDistance(player); // Get Client Side View Distance
        if (viewDistance > clientSideViewDistance + moreThanSettings) { // If given view distance is more than client side view distance
            viewDistance = clientSideViewDistance + moreThanSettings;
        }
        return viewDistance;
    }

    // A FUNCTION FOR CLIENT SUPPORTED VIEW DISTANCE. MAKE SURE THE REAL VIEW DISTANCE DOESN'T GET OVER SUPPORTED
    static int limitSupportedView(Player player, int viewDistance) {
        if (viewDistance > playerViewDistance.get(player.getName())) {
            viewDistance = playerViewDistance.get(player.getName());
        }
        return viewDistance;
    }

    // MAKE SURE REDUCTION INDICE ISN'T OVER LIMITS
    public static void limitReductionIndice() {
        if (reductionIndice > maxIndice) { // Make sure the reduction indice don't escape limits
            reductionIndice = maxIndice;
        } else if (reductionIndice < 0) {
            reductionIndice = 0.0;
        }
    }
}
