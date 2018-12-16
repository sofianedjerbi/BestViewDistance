package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.async.QuitDataUnload;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class OnQuit implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public static void onPlayerQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(BestViewDistance.plugin, new QuitDataUnload(event.getPlayer().getName())); // Unload Data with Async Method
    }
}