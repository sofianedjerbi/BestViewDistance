package me.lxct.bestviewdistance;

import static me.lxct.bestviewdistance.functions.get.*;
import static me.lxct.bestviewdistance.functions.gen.*;
import static me.lxct.bestviewdistance.functions.set.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;

public class main extends org.bukkit.plugin.java.JavaPlugin
{

    public main() {}
    public void onEnable() {
        // WARNING
        Bukkit.getLogger().info("[BestViewDistance] -------------------------------------------------");
        Bukkit.getLogger().info("[BestViewDistance] Best View Distance By LXCT => WARNING :");
        Bukkit.getLogger().info("[BestViewDistance] Make sure you use this plugin with Paper !");
        Bukkit.getLogger().info("[BestViewDistance] https://papermc.io/ <3");
        Bukkit.getLogger().info("[BestViewDistance] -------------------------------------------------");
        // WARNING
        genServerData();
        genConfig(); // CREATING CONFIG.YML
        // SCHEDULER
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            double tps = Bukkit.getTPS()[0]; // Get TPS
            setServerReductionIndice(getNewReductionIndice(tps)); // Update Reduction Indice
            setPlayersBestViewDistance(getNewReductionIndice(tps)); // Update Players View Distance
        }, 0L, 20*getDelayViewDistance());
        // SCHEDULER END
    }


    @org.bukkit.event.EventHandler
    public void playerLogin(PlayerLoginEvent event){
        // Create player.yml
        Player player = event.getPlayer();
        genPlayerData(player);
        // Apply Config
        int ViewDistance = getPlayerViewDistance(player);
        player.setViewDistance(ViewDistance);
    }




}