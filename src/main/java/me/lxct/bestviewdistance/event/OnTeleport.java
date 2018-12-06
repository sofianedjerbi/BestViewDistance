package me.lxct.bestviewdistance.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import static me.lxct.bestviewdistance.functions.data.Variable.onteleportview;


public class OnTeleport implements Listener {
    @EventHandler
    public static void onPlayerTeleport(PlayerTeleportEvent event) {
        event.getPlayer().setViewDistance(onteleportview);
    }
}