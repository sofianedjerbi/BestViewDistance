package me.lxct.bestviewdistance.event;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.lxct.bestviewdistance.functions.Variable.afkList;


public class OnPlayerMove implements Listener {
    @EventHandler
    public static void playerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Unset Afk...
        afkList.remove(player.getName());
    }
}
