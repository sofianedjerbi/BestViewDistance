package me.lxct.bestviewdistance.functions.hooks;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static me.lxct.bestviewdistance.functions.Get.get1minTPS;
import static me.lxct.bestviewdistance.functions.Get.getSettingsViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

@SuppressWarnings("deprecation")
public class PlaceholderAPIHook extends EZPlaceholderHook {

    PlaceholderAPIHook(Plugin plugin) {
        super(plugin, "VDIST");
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equals("REDUCTION_INDICE_DECIMAL")) {
            return String.valueOf((Math.round(reductionIndice * Math.pow(10, decimalsIndice))) / Math.pow(10, decimalsIndice));
        }
        if (identifier.equals("REDUCTION_INDICE")) {
            return String.valueOf(Math.round(reductionIndice * 100));
        }
        if (identifier.equals("DECIMAL_TPS")) {
            return String.valueOf((Math.round(get1minTPS() * Math.pow(10, decimalsTPS))) / Math.pow(10, decimalsTPS));
        }
        if (identifier.equals("TPS")) {
            return String.valueOf(Math.round(get1minTPS()));
        }
        if (player == null) {
            return "";
        }
        if (identifier.equals("PLAYER_SETTINGS_VIEW")) {
            return String.valueOf(getSettingsViewDistance(player));
        }
        if (identifier.equals("PLAYER_SUPPORTED_VIEW")) {
            return String.valueOf(playerViewDistance.get(player.getName()));
        }
        if (identifier.equals("PLAYER_CURRENT_VIEW")) {
            return String.valueOf(player.getViewDistance());
        }
        return null;
    }
}