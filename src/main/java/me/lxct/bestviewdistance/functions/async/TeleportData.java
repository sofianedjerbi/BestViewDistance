package me.lxct.bestviewdistance.functions.async;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.sync.UnsetTeleportViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Set.setPlayerPermissions;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class TeleportData implements Runnable {

    private Player player;

    public TeleportData(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (playerLiveViewDistance.get(player.getName()) != null) { // If he got a live view
            if (waitForTPUnset.get(player.getName()) != null) { // If he is waiting for TP UNSET
                Bukkit.getScheduler().cancelTask(waitForTPUnset.get(player.getName())); // Cancel task if the player got a task
                waitForTPUnset.remove(player.getName()); // Remove waiting
            }
            try {
                int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, new UnsetTeleportViewDistance(player, setPlayerPermissions(player, playerLiveViewDistance.get(player.getName()))), teleportUnsetDelay * 20); // Unset teleport
                if (task == -1 && useTasks) { // If the task has failed
                    Bukkit.getScheduler().runTaskLater(BestViewDistance.plugin, new UnsetTeleportViewDistance(player, setPlayerPermissions(player, playerLiveViewDistance.get(player.getName()))), teleportUnsetDelay * 20); // Force unset teleport
                } else {  // If the task has been successfully scheduled
                    waitForTPUnset.put(player.getName(), task); // Set waiting
                }
            } catch (NullPointerException ignored) {
            }
        }
    }
}