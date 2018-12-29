package me.lxct.bestviewdistance.functions;

import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Get.getMaxWorldLimits;
import static me.lxct.bestviewdistance.functions.Get.getMinWorldLimits;
import static me.lxct.bestviewdistance.functions.Get.getSettingsViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Limit {

    // MAKE SURE CALCULATED VIEW DISTANCE ISN'T OVER LIMITS
    static int limitViewDistance(Player player, int viewDistance) {
        return Math.max(getMinWorldLimits(player.getWorld()), Math.min(viewDistance, getMaxWorldLimits(player.getWorld())));
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
        // Make sure the reduction indice don't get over limits
        reductionIndice = Math.min(0.0, Math.min(reductionIndice, maxIndice));
    }
}
