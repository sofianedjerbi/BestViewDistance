package me.lxct.bestviewdistance.functions.util;

import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.permissionsBypassAFK;
import static me.lxct.bestviewdistance.functions.data.Variable.permissionsBypassFlying;

public class Checkers {
    public static void AFKChecker() { // What this function does ? if the player has exactly the same position as x minutes ago, he'll be set in "AFK" mode.
        for (final Player p : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            final BVDPlayer player = new BVDPlayer(p);
            final Location location = player.getLocation(); // Get Location

            if (player.isViewBypass() && permissionsBypassAFK) {
                if (location.equals(player.getAfkLocation()) && !player.isAfk()) { // If same position ...
                    player.setAfk(true); // SET AFK
                } else { // If it's not the same position...
                    player.setAfkLocation(player.getLocation()); // Actualize the position.
                }
            } else {
                if (location.equals(player.getAfkLocation()) && !player.isAfk()) { // If same position ...
                    if (!player.isAfk()) { // If player is not afk
                        player.setAfk(true); // SET AFK
                    }
                } else { // If it's not the same position...
                    player.setAfkLocation(player.getLocation()); // Actualize the position.
                }
            }
        }
    }

    public static void flyingChecker() { // What this function does ? if the player is still flying, we'll set this view distance to "flying" value
        for (final Player p : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            final BVDPlayer player = new BVDPlayer(p);

            if (player.isViewBypass() && permissionsBypassFlying) {
                if (p.isFlying()) { // If flying
                    if (!player.isFlying()) { // IF flying && not in the list
                        player.setFlying(true);
                    }
                } else { // If not flying
                    player.setFlying(false);
                }
            } else {
                if (p.isFlying()) { // If flying
                    if (!player.isFlying()) { // IF flying && not in the list
                        player.setFlying(true);
                    }
                } else { // If not flying
                    player.setFlying(false);
                }
            }
        }
    }
}
