package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.lxct.bestviewdistance.functions.data.Variable.*;


public class OnJoin implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public static void onPlayerJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        onlinePlayers.remove(p);  // Prevent from duplications
        BVDPlayer data = new BVDPlayer(p);
        onlinePlayers.put(p, data);
        if (useLoginView) {
            data.setViewDistanceATBS(onLoginView, onLoginDelay);
        } else {
            data.setViewDistanceATBS(min, onLoginDelay);
        }
    }
}