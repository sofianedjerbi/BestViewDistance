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
    public static void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        onlinePlayers.put(p, new BVDPlayer(p));
        if(useLoginView) {
            p.setViewDistance(onLoginView);
        } else {
            p.setViewDistance(min);
        }
    }
}