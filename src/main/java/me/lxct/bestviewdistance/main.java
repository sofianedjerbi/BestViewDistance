package me.lxct.bestviewdistance;

import me.lxct.bestviewdistance.commands.viewCommand;
import me.lxct.bestviewdistance.event.onLogin;
import me.lxct.bestviewdistance.event.onPlayerMove;
import me.lxct.bestviewdistance.functions.other;
import me.lxct.bestviewdistance.functions.variable;
import org.bukkit.Bukkit;

import static me.lxct.bestviewdistance.functions.gen.*;
import static me.lxct.bestviewdistance.functions.get.getActualReductionIndice;
import static me.lxct.bestviewdistance.functions.get.getNewReductionIndice;
import static me.lxct.bestviewdistance.functions.other.loadServerReductionIndice;
import static me.lxct.bestviewdistance.functions.other.savePlayerViewDistance;
import static me.lxct.bestviewdistance.functions.other.saveReductionIndice;
import static me.lxct.bestviewdistance.functions.set.setPlayersBestViewDistance;
import static me.lxct.bestviewdistance.functions.set.setServerLimits;

public class main extends org.bukkit.plugin.java.JavaPlugin
{
    public static main plugin;

    @Override
    public void onEnable(){
        plugin=this; // Allow main.plugin
        getServer().getPluginManager().registerEvents(new onLogin(), this); // Add OnLogin Event
        getServer().getPluginManager().registerEvents(new onPlayerMove(), this); // Add OnPlayerMove Event
        saveDefaultConfig(); // GENERATE CONFIG
        // WARNING
        Bukkit.getLogger().info("╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐");
        Bukkit.getLogger().info("╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤ ");
        Bukkit.getLogger().info("╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘");
        Bukkit.getLogger().info("╚ Make sure you use this plugin with Paper.");
        Bukkit.getLogger().info("╚ https://papermc.io/");
        Bukkit.getLogger().info("╚ Best View Distance, By Lxct. ");
        // WARNING
        genFolders(); // CREATING /plugins/BestViewDistance/data/
        genServerData(); // CREATING SERVER.YML
        genAllOnlinePlayerData(); // In case of a /reload
        loadServerReductionIndice();
        getCommand("view").setExecutor(new viewCommand());
        getCommand("vdist").setExecutor(new viewCommand());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, calculations, 0L,this.getConfig().getInt("ViewDistance.Delay")*20L); // CALCULATIONS SCHEDULER
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, detectAFK, 0L,this.getConfig().getInt("Performances.AFKTimer")*20L); // DETECT AFK SCHEDULER
    }

    @Override
    public void onDisable(){
        Bukkit.getLogger().info("[BestViewDistance] Saving players data...");
        savePlayerViewDistance(); // Save data of all players
        saveReductionIndice(variable.reductionIndice); // Save Reduction Indice
        Bukkit.getLogger().info("[BestViewDistance] Players data saved !");
    }

    private Runnable calculations = // CALCULATIONS
            () -> {
                variable.reductionIndice = getNewReductionIndice(Bukkit.getTPS()[0]); // Update Reduction Indice
                setServerLimits(); // Control
                setPlayersBestViewDistance(getActualReductionIndice()); // Update Players View Distance
            };

    private Runnable detectAFK = // CHECK IF AFK
            other::putPlayerAFK;
}