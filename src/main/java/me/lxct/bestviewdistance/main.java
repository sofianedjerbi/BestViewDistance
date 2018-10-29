package me.lxct.bestviewdistance;

import org.bukkit.Bukkit;

public class main extends org.bukkit.plugin.java.JavaPlugin
{
    public main() {}
    public void onEnable() {
        Bukkit.getLogger().info("[BestViewDistance] -------------------------------------------------");
        Bukkit.getLogger().info("[BestViewDistance] Best View Distance By LXCT => WARNING :");
        Bukkit.getLogger().info("[BestViewDistance] Make sure you use this plugin with Paper !");
        Bukkit.getLogger().info("[BestViewDistance] https://papermc.io/");
        Bukkit.getLogger().info("[BestViewDistance] -------------------------------------------------");
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            double oldTPS = Bukkit.getTPS()[0];
            // TESSST

            // TEEEEST
        }, 0L, 200L);
    }



}