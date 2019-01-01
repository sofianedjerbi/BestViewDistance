package me.lxct.bestviewdistance.functions.util;

import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Misc {


    public static void genOnlinePlayerData() { // Set all playerLiveViewDistance to onLoginView.
        onlinePlayers.clear();
        for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
            onlinePlayers.put(p, new BVDPlayer(p));
        }
    }


    public static String replacePlaceHolders(String string) { // Replace placeholders in messages.yml
        string = string.replace("%TPS%", String.valueOf(timings.get1minTPS()));
        string = string.replace("%PLAYER%", playerData.getName());
        string = string.replace("%VIEWDISTANCE%", String.valueOf(playerData.getCurrentViewDistance()));
        string = string.replace("%SETTINGS%", String.valueOf(playerData.getSettingsViewDistance()));
        string = string.replace("%REDUCTIONINDICE%", String.valueOf(Math.round(timings.getReductionIndice() * 100)));
        string = string.replace("%PING%", String.valueOf(playerData.getPing()));
        string = string.replace("%PINGVIEW%", String.valueOf(playerData.getSupportedViewDistance()));
        return string;
    }
}