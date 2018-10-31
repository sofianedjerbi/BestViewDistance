package me.lxct.bestviewdistance.event;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.lxct.bestviewdistance.functions.variable.afkList;


public class onPlayerMove implements Listener {
    @EventHandler
    public static void onPlayerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        afkList.remove(player); // Unset Afk...
    }
}
