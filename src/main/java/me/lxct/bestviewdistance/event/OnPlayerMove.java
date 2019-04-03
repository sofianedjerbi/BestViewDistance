package me.lxct.bestviewdistance.event;
import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.async.UnsetAfk;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class OnPlayerMove implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public static void playerMove(final PlayerMoveEvent e) {
        if (e.getPlayer().hasMetadata("NPC")) {
            return;
        }

        // Unset Afk with Async Method...
        Bukkit.getScheduler().runTaskAsynchronously(BestViewDistance.plugin, new UnsetAfk(e));
    }
}
