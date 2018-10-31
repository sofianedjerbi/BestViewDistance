package me.lxct.bestviewdistance;

import me.lxct.bestviewdistance.commands.viewCommand;
import me.lxct.bestviewdistance.event.onLogin;
import org.bukkit.Bukkit;

import static me.lxct.bestviewdistance.functions.gen.genFolders;
import static me.lxct.bestviewdistance.functions.gen.genServerData;
import static me.lxct.bestviewdistance.functions.get.getActualReductionIndice;
import static me.lxct.bestviewdistance.functions.get.getNewReductionIndice;
import static me.lxct.bestviewdistance.functions.set.*;

public class main extends org.bukkit.plugin.java.JavaPlugin
{
    public static main plugin;

    @Override
    public void onEnable() {
        plugin=this; // Allow main.plugin
        getServer().getPluginManager().registerEvents(new onLogin(), this); // Add OnLogin Event
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
        getCommand("view").setExecutor(new viewCommand());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, task, 0L,this.getConfig().getInt("ViewDistance.Delay")*20L); // SCHEDULER
    }
    private Runnable task = // CALCULATIONS
            () -> {
                Bukkit.broadcastMessage("task");
                setServerReductionIndice(getNewReductionIndice(Bukkit.getTPS()[0])); // Update Reduction Indice
                setServerLimits(); // Control
                setPlayersBestViewDistance(getActualReductionIndice()); // Update Players View Distance
            };

}