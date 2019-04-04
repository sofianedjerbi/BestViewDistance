package me.lxct.bestviewdistance.functions.async;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.BVDPlayer;
import me.lxct.bestviewdistance.functions.sync.SetViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.onlinePlayers;
import static me.lxct.bestviewdistance.functions.data.Variable.teleportUnsetDelay;
import static me.lxct.bestviewdistance.functions.util.Scheduler.scheduleSync;

public class TeleportData implements Runnable {

    private final Player p;

    public TeleportData(final Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        final BVDPlayer player = onlinePlayers.get(p);
        if (player == null) {
            return;
        }

        if (player.isWaitingForTpUnset()) { // If he is waiting for TP UNSET
            Bukkit.getScheduler().cancelTask(player.getTpTaskId()); // Cancel task if the player got a task
            player.setWaitingForTpUnset(false); // Remove waiting
        }
        scheduleSync(new SetViewDistance(p, player.getScheduledViewDistance()), teleportUnsetDelay * 20, player);
        player.setWaitingForTpUnset(true);
        Bukkit.getScheduler().runTaskLaterAsynchronously(BestViewDistance.plugin, new UnsetTeleport(p), teleportUnsetDelay * 20); // Force unset teleport
    }
}