package me.lxct.bestviewdistance.functions.util;

import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Misc {
    // A FUNCTION THAT SET THE VIEW DISTANCE WITH FUNCTION THAT BREAK ASYNC CHAINS
    public static void applyViewDistance() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            BVDPlayer player = new BVDPlayer(p);
            if (player.isAfk()
                    && player.getCurrentViewDistance() != afk
                    && useAFKView) { // AFK VIEW
                player.setViewDistance(afk);
            } else if (player.isFlying()
                    && player.getCurrentViewDistance() != onFlyingView
                    && useOnFlyingView) { // FLYING VIEW
                player.setViewDistance(onFlyingView);
            } else {
                if (!player.isWaitingForTpUnset()) {
                    int vdistToApply = player.getViewBypass(player.getSheduledViewDistance());
                    if (vdistToApply != player.getCurrentViewDistance()) {
                        player.setViewDistance(vdistToApply);
                    }
                }
            }
        }
    }

    public static void genOnlinePlayerData() { // Set all playerLiveViewDistance to onLoginView.
        onlinePlayers.clear();
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
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