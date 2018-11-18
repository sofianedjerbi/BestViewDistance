package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.async.LoginDataLoad;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.lxct.bestviewdistance.functions.Get.getPlayerCustomViewBoolean;
import static me.lxct.bestviewdistance.functions.Get.getPlayerCustomViewDistance;
import static me.lxct.bestviewdistance.functions.Get.getServerCustomViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.playerViewSet;
import static me.lxct.bestviewdistance.functions.data.Variable.serverViewSet;


public class OnLogin implements Listener {
    @EventHandler
    public static void onPlayerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(BestViewDistance.plugin, new LoginDataLoad(player)); // Load Data with Async Method
        if (getPlayerCustomViewBoolean(player)) {
            if(!playerViewSet.containsKey(player.getName())) {
                playerViewSet.put(player.getName(), getPlayerCustomViewDistance(player));
            }
            player.setViewDistance(getPlayerCustomViewDistance(player));
        } else {
            if (!serverViewSet) {
                player.setViewDistance(Variable.onloginview);
            } else {
                player.setViewDistance(getServerCustomViewDistance());
            }
        }
    }
}