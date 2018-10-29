package me.lxct.bestviewdistance;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class main extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener
{
    public main() {}
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }



    @org.bukkit.event.EventHandler
    public void regenHealth(EntityRegainHealthEvent event) {
        if(event.getEntityType() == EntityType.PLAYER) {
            Player p = (Player) event.getEntity();
            if (p.hasPermission("uhc.on")) {
                if(event.getRegainReason() == RegainReason.SATIATED) {
                    event.setCancelled(true);
                }
            }
        }
    }
}