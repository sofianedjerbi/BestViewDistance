package me.lxct.bestviewdistance;

import me.lxct.bestviewdistance.commands.ViewCommand;
import me.lxct.bestviewdistance.event.*;
import me.lxct.bestviewdistance.functions.Other;
import me.lxct.bestviewdistance.functions.async.AsyncUpdateChecker;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

import static me.lxct.bestviewdistance.functions.Calculations.calculatePlayersBestViewDistance;
import static me.lxct.bestviewdistance.functions.Get.*;
import static me.lxct.bestviewdistance.functions.Limit.limitReductionIndice;
import static me.lxct.bestviewdistance.functions.Other.*;
import static me.lxct.bestviewdistance.functions.UpdateConfig.updateConfig;
import static me.lxct.bestviewdistance.functions.Warnings.checkProtocolLib;
import static me.lxct.bestviewdistance.functions.Warnings.checkServerView;
import static me.lxct.bestviewdistance.functions.data.Variable.loadVariables;
import static me.lxct.bestviewdistance.functions.data.Variable.useTeleportView;
import static me.lxct.bestviewdistance.functions.hooks.Hooks.checkHooks;

public class BestViewDistance extends JavaPlugin {

    public static BestViewDistance plugin;

    @Override
    public void onEnable() {

        plugin = this; // Allow BestViewDistance.plugin

        // WARNING
        Bukkit.getLogger().info("╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐"); // Display
        Bukkit.getLogger().info("╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤ ");
        Bukkit.getLogger().info("╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘");
        Bukkit.getLogger().info("└ Version: " + plugin.getDescription().getVersion());
        Bukkit.getLogger().info("└ Make sure you use this plugin with Paper.");
        Bukkit.getLogger().info("└ https://papermc.io/");
        Bukkit.getLogger().info("└ Best View Distance, By Lxct.");
        // WARNING

        //
        // Retro compatibility
        //

        checkHooks(this);

        if (Bukkit.getVersion().contains("1.8")) {
            try {
                serverInstance = getNMSClass("MinecraftServer").getMethod("getServer").invoke(null);
                tpsField = serverInstance.getClass().getField("recentTps");
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        //
        // Retro compatibility
        //

        //
        // Load & Get info
        //

        // UPDATE CONFIG
        updateConfig();

        // WARNINGS
        checkProtocolLib();
        checkServerView();
        // WARNINGS

        // GENERATION
        saveDefaultConfig(); // GENERATE
        genMessagesYml(); // Generate Messages.yml
        loadMessagesYml(); // Load CustomConfig (Messages)
        loadVariables(); // Load Variables (Config / Messages)
        genOnlinePlayerData(); // In case of a reload caused by another plugin
        // GENERATION

        // EVENTS
        getServer().getPluginManager().registerEvents(new OnJoin(), this); // Add OnLogin Event
        getServer().getPluginManager().registerEvents(new OnQuit(), this); // Add OnQuit Event
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this); // Add OnPlayerMove Event
        if (useTeleportView) {
            getServer().getPluginManager().registerEvents(new OnTeleport(), this); // Add OnTeleport Event
        }
        // EVENTS

        // UPDATE CONFIG

        // COMMANDS
        getCommand("view").setExecutor(new ViewCommand()); // Executor for commands
        getCommand("vdist").setExecutor(new ViewCommand());
        getCommand("vping").setExecutor(new ViewCommand());
        getCommand("view").setTabCompleter(new OnTabComplete()); // Tab completer
        // COMMANDS

        //
        // Load & Get info
        //

        //
        // Schedule tasks
        //

        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, applyViewDistance, 0L, this.getConfig().getInt("Delay.SetViewDelay") * 20L); // CALCULATIONS SCHEDULER
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, detectAFK, 0L, this.getConfig().getInt("Delay.AFKDelay") * 20L); // DETECT AFK SCHEDULER
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, detectFlying, 0L, this.getConfig().getInt("Delay.CheckFlyingDelay") * 20L); // DETECT AFK SCHEDULER
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, calculations, 0L, this.getConfig().getInt("Delay.CalculationsDelay") * 20L);

        //
        // BSTATS + ASYNC UPDATE CHECKER
        //

        //if (this.getConfig().getBoolean("Misc.Metrics")) {
        //noinspection unused
        Metrics metrics = new Metrics(this); // METRICS
        //}
        if (this.getConfig().getBoolean("Misc.CheckUpdates")) {
            new AsyncUpdateChecker(this).checkForUpdate(); // Add AsyncUpdateChecker (Thx Benz56)
        }
    }

    private Runnable calculations = // CALCULATIONS
            () -> {
                Variable.reductionIndice = getNewReductionIndice(get1minTPS()); // Update Reduction Indice
                limitReductionIndice(); // Control
                calculatePlayersBestViewDistance(Variable.reductionIndice); // Update Players View Distance
            };

    // Update Players View Distance
    private Runnable applyViewDistance = // CALCULATIONS
            Other::applyViewDistance;

    private Runnable detectAFK = // CHECK IF AFK
            Other::AFKchecker;

    private Runnable detectFlying = // CHECK IF AFK
            Other::flyingChecker;
}