package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.async.PreLoginDataLoad;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;


public class OnAsyncPreLogin implements Listener {
    @EventHandler
    public static void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(BestViewDistance.plugin, new PreLoginDataLoad(event.getName())); // Load Data with Async Method
    }
}