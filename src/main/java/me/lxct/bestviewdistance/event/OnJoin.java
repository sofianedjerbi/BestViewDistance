package me.lxct.bestviewdistance.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.lxct.bestviewdistance.functions.data.Variable.playerLiveViewDistance;


public class OnJoin implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setViewDistance(playerLiveViewDistance.get(player.getName()));
    }
}