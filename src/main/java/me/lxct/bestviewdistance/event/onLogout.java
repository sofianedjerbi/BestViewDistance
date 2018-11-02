package me.lxct.bestviewdistance.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import static me.lxct.bestviewdistance.functions.set.setPlayerViewDistance;
import static me.lxct.bestviewdistance.functions.variable.playerViewDistance;


public class onLogout implements Listener {
    @EventHandler
    public static void onPlayerLogout(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        setPlayerViewDistance(player, playerViewDistance.get(player.getUniqueId())); // SAVE PLAYER DATA
    }
}