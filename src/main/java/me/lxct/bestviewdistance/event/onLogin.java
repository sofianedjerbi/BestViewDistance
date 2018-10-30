package me.lxct.bestviewdistance.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.lxct.bestviewdistance.functions.get.getMinViewDistance;

public class onLogin implements Listener {
    @EventHandler
    public static void onPlayerLogin(PlayerJoinEvent event){
        event.getPlayer().setViewDistance(getMinViewDistance());
    }

}
