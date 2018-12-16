package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.async.TeleportData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import static me.lxct.bestviewdistance.functions.data.Variable.onTeleportView;
import static me.lxct.bestviewdistance.functions.data.Variable.waitForTPUnset;


public class OnTeleport implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public static void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (event.getCause() != PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT && event.getCause() != PlayerTeleportEvent.TeleportCause.UNKNOWN && event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            if (!waitForTPUnset.containsKey(player.getName())) { // If he's not waiting for tp unset
                player.setViewDistance(onTeleportView); // Set on teleport view
            }
            Bukkit.getScheduler().runTaskAsynchronously(BestViewDistance.plugin, new TeleportData(player)); // Process teleport data with async method
        }
    }
}