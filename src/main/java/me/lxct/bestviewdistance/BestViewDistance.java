package me.lxct.bestviewdistance;

import me.lxct.bestviewdistance.commands.ViewCommand;
import me.lxct.bestviewdistance.event.OnLogin;
import me.lxct.bestviewdistance.event.OnPlayerMove;
import me.lxct.bestviewdistance.functions.Other;
import me.lxct.bestviewdistance.functions.Variable;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;

import static me.lxct.bestviewdistance.functions.Get.getNewReductionIndice;
import static me.lxct.bestviewdistance.functions.Set.setPlayersBestViewDistance;
import static me.lxct.bestviewdistance.functions.Set.setServerLimits;

public class BestViewDistance extends org.bukkit.plugin.java.JavaPlugin
{
    public static BestViewDistance plugin;

    @Override
    public void onEnable(){
        plugin=this; // Allow main.plugin
        getServer().getPluginManager().registerEvents(new OnLogin(), this); // Add OnLogin Event
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this); // Add OnPlayerMove Event
        saveDefaultConfig(); // GENERATE CONFIG
        // WARNING
        Bukkit.getLogger().info("╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐"); // Display
        Bukkit.getLogger().info("╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤ ");
        Bukkit.getLogger().info("╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘");
        Bukkit.getLogger().info("╚ Make sure you use this plugin with Paper.");
        Bukkit.getLogger().info("╚ https://papermc.io/");
        Bukkit.getLogger().info("╚ Best View Distance, By Lxct. ");
        // WARNING
        getCommand("view").setExecutor(new ViewCommand()); // Executor for commands
        getCommand("vdist").setExecutor(new ViewCommand());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, calculations, 0L,this.getConfig().getInt("ViewDistance.Delay")*20L); // CALCULATIONS SCHEDULER
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, detectAFK, 0L,this.getConfig().getInt("Performances.AFKTimer")*20L); // DETECT AFK SCHEDULER
        Metrics metrics = new Metrics(this); // METRICS
    }

    private Runnable calculations = // CALCULATIONS
            () -> {
                Variable.reductionIndice = getNewReductionIndice(Bukkit.getTPS()[0]); // Update Reduction Indice
                setServerLimits(); // Control
                setPlayersBestViewDistance(Variable.reductionIndice); // Update Players View Distance
            };

    private Runnable detectAFK = // CHECK IF AFK
            Other::putPlayerAFK;
}