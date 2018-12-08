package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.sync.SetViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import static me.lxct.bestviewdistance.functions.Set.setPlayerPermissions;
import static me.lxct.bestviewdistance.functions.data.Variable.onteleportview;
import static me.lxct.bestviewdistance.functions.data.Variable.playerLiveViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.teleportunset;
import static org.bukkit.event.player.PlayerTeleportEvent.TeleportCause.*;


public class OnTeleport implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public static void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!event.getCause().equals(CHORUS_FRUIT) && !event.getCause().equals(ENDER_PEARL) && !event.getCause().equals(UNKNOWN)) {
            player.setViewDistance(onteleportview);
            if (playerLiveViewDistance.containsKey(player.getName())) {
                int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, new SetViewDistance(player, setPlayerPermissions(player, playerLiveViewDistance.get(player.getName())/2)), teleportunset * 20); // Unset teleport
                if (task == -1) {
                    Bukkit.getScheduler().runTaskLater(BestViewDistance.plugin, new SetViewDistance(player, setPlayerPermissions(player, playerLiveViewDistance.get(player.getName())/2)), teleportunset * 20);
                }
            }
        }
    }
}
