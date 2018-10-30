package me.lxct.bestviewdistance.event;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class onLogin implements Listener {
    @org.bukkit.event.EventHandler
    public static void onPlayerLogin(PlayerLoginEvent event){
        event.getPlayer().setViewDistance(4);
    }

}
