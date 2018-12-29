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

        if (viewDistance >= max) {
            return max;
        } else if (viewDistance <= min) {
            return min;
        }

        return viewDistance;
    }

    // A FUNCTION FOR CLIENT SIDE SETTING. DON'T GIVE MORE VIEW DISTANCE THAN REQUIRED.
    static int limitClientSetting(Player player, int viewDistance) {
        return Math.min(viewDistance, getSettingsViewDistance(player) + moreThanSettings);
    }
    // A FUNCTION FOR CLIENT SUPPORTED VIEW DISTANCE. MAKE SURE THE REAL VIEW DISTANCE DOESN'T GET OVER SUPPORTED
    static int limitSupportedView(Player player, int viewDistance) {
        return Math.min(viewDistance, playerViewDistance.get(player.getName()));
    }
    // MAKE SURE REDUCTION INDICE ISN'T OVER LIMITS
    public static void limitReductionIndice() {
        if (reductionIndice > maxIndice) { // Make sure the reduction indice don't get over limits
            reductionIndice = maxIndice;
        } else if (reductionIndice < 0) {
            reductionIndice = 0.0;
        }
    }
}
