package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Get.getSettingsViewDistance;

public class Limit {

    // MAKE SURE CALCULATED VIEW DISTANCE ISN'T OVER LIMITS
    static int limitViewDistance(int viewDistance) {
        if (viewDistance > Variable.max) {
            viewDistance = Variable.max;
        } else if (viewDistance < Variable.min) {
            viewDistance = Variable.min;
        }
        return viewDistance;
    }

    // A FUNCTION FOR CLIENT SIDE SETTING. DON'T GIVE MORE VIEW DISTANCE THAN REQUIRED.
    static int limitClientSetting(Player player, int viewDistance) {
        int clientSideViewDistance = getSettingsViewDistance(player); // Get Client Side View Distance
        if (viewDistance > clientSideViewDistance) { // If given view distance is more than client side view distance
            viewDistance = clientSideViewDistance;
        }
        return viewDistance;
    }

    // MAKE SURE REDUCTION INDICE ISN'T OVER LIMITS
    public static void limitReductionIndice() {
        if (Variable.reductionIndice > Variable.maxIndice) { // Make sure the reduction indice don't escape limits
            Variable.reductionIndice = Variable.maxIndice;
        } else if (Variable.reductionIndice < 0) {
            Variable.reductionIndice = 0.0;
        }
    }
}
