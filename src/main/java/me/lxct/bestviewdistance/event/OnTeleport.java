package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.BVDPlayer;
import me.lxct.bestviewdistance.functions.async.TeleportData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import static me.lxct.bestviewdistance.functions.data.Variable.*;


public class OnTeleport implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public static void onPlayerTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        BVDPlayer player = onlinePlayers.get(p);
        if (e.getCause() != PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT && e.getCause() != PlayerTeleportEvent.TeleportCause.UNKNOWN && e.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            if (player.isViewBypass() && permissionsBypassTeleport) {
                if (!player.isWaitingForTpUnset()) { // If he's not waiting for tp unset
                    p.setViewDistance(onTeleportView); // Set on teleport view
                }
                Bukkit.getScheduler().runTaskAsynchronously(BestViewDistance.plugin, new TeleportData(p)); // Process teleport data with async method
            } else {
                if (!player.isWaitingForTpUnset()) { // If he's not waiting for tp unset
                    p.setViewDistance(onTeleportView); // Set on teleport view
                }
                Bukkit.getScheduler().runTaskAsynchronously(BestViewDistance.plugin, new TeleportData(p)); // Process teleport data with async method
            }
        }
    }
}