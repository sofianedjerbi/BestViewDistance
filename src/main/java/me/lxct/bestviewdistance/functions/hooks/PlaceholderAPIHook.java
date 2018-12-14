package me.lxct.bestviewdistance.functions.hooks;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import me.lxct.bestviewdistance.functions.Get;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static me.lxct.bestviewdistance.functions.data.Variable.*;

@SuppressWarnings("deprecation")
public class PlaceholderAPIHook extends EZPlaceholderHook {

    PlaceholderAPIHook(Plugin plugin) {
        super(plugin, "VDIST");
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        if (identifier.equals("PLAYER_SETTINGS_VIEW")) {
            return String.valueOf(Get.getViewDistance(player));
        }
        if (identifier.equals("PLAYER_SUPPORTED_VIEW")) {
            return String.valueOf(playerViewDistance.get(player.getName()));
        }
        if (identifier.equals("PLAYER_LIVE_VIEW")) {
            return String.valueOf(playerLiveViewDistance.get(player.getName()));
        }
        if (identifier.equals("REDUCTION_INDICE_DECIMAL")) {
            return String.valueOf(reductionIndice);
        }
        if (identifier.equals("REDUCTION_INDICE")) {
            return String.valueOf(Math.round(reductionIndice * 100));
        }
        if (identifier.equals("DECIMAL_TPS")) {
            return String.valueOf(Get.get1minTPS());
        }
        return null;
    }
}