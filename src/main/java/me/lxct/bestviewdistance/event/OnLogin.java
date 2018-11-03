package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.functions.Variable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.lxct.bestviewdistance.functions.Variable.playerViewDistance;


public class OnLogin implements Listener {
    @EventHandler
    public static void onPlayerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!playerViewDistance.containsKey(player.getName())) {
            playerViewDistance.put(player.getName(), Variable.min); // LOAD PLAYER DATA
        }
        player.setViewDistance(Variable.min);
    }
}