package me.lxct.bestviewdistance.functions.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.entity.Player;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "Kugge";
    }

    @Override
    public String getIdentifier() {
        return "VDIST";
    }

    @Override
    public String getVersion() {
        return "12";
    }
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier.equals("REDUCTION_INDICE_DECIMAL")) {
            return String.valueOf((Math.round(timings.getReductionIndice() * Math.pow(10, decimalsIndice))) / Math.pow(10, decimalsIndice));
        }
        if (identifier.equals("REDUCTION_INDICE")) {
            return String.valueOf(Math.round(timings.getReductionIndice() * 100));
        }
        if (identifier.equals("DECIMAL_TPS")) {
            return String.valueOf((Math.round(timings.get1minTPS() * Math.pow(10, decimalsTPS))) / Math.pow(10, decimalsTPS));
        }
        if (identifier.equals("TPS")) {
            return String.valueOf(Math.round(timings.get1minTPS()));
        }
        if (p == null) {
            return "";
        }
        final BVDPlayer player = onlinePlayers.get(p);
        if (identifier.equals("PLAYER_SETTINGS_VIEW")) {
            return String.valueOf(player.getSettingsViewDistance());
        }
        if (identifier.equals("PLAYER_SUPPORTED_VIEW")) {
            return String.valueOf(player.getSupportedViewDistance());
        }
        if (identifier.equals("PLAYER_CURRENT_VIEW")) {
            return String.valueOf(p.getViewDistance());
        }
        if (identifier.equals("PLAYER_PING")) {
            return String.valueOf(player.getPing());
        }
        return null;
    }
}
