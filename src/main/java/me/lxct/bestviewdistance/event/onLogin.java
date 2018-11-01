package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.functions.variable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.lxct.bestviewdistance.functions.gen.genPlayerData;


public class onLogin implements Listener {
    @EventHandler
    public static void onPlayerLogin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        genPlayerData(player);
        event.getPlayer().setViewDistance(variable.min);
    }

}