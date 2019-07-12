package me.lxct.bestviewdistance;

import me.lxct.bestviewdistance.commands.ViewCommand;
import me.lxct.bestviewdistance.event.*;
import me.lxct.bestviewdistance.functions.async.AsyncUpdateChecker;
import me.lxct.bestviewdistance.functions.util.Calculations;
import me.lxct.bestviewdistance.functions.util.Checkers;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static me.lxct.bestviewdistance.functions.data.Variable.*;
import static me.lxct.bestviewdistance.functions.hooks.Hooks.checkHooks;
import static me.lxct.bestviewdistance.functions.util.Misc.genOnlinePlayerData;
import static me.lxct.bestviewdistance.functions.util.UpdateConfig.updateConfig;
import static me.lxct.bestviewdistance.functions.util.Warnings.*;

public class BestViewDistance extends JavaPlugin {

    public static BestViewDistance plugin;

    @Override
    @SuppressWarnings("deprecation")
    public void onEnable() {

        plugin = this; // Allow BestViewDistance.plugin

        // WARNING
        Bukkit.getLogger().info("╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐"); // Display
        Bukkit.getLogger().info("╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤ ");
        Bukkit.getLogger().info("╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘");
        Bukkit.getLogger().info("└ Version: " + plugin.getDescription().getVersion());
        Bukkit.getLogger().info("└ Make sure you use this plugin with Paper.");
        Bukkit.getLogger().info("└ https://papermc.io/");
        Bukkit.getLogger().info("└ Best View Distance, By Kugge.");
        // WARNING

        checkHooks(this); // Looking for dependencies

        //
        // Load & Get info
        //

        // UPDATE CONFIG
        updateConfig();

        // WARNINGS
        checkProtocolLib();
        checkServerView();
        checkCompatibility();
        // WARNINGS

        // GENERATION
        saveDefaultConfig(); // GENERATE
        loadVariables(); // Load Variables (Config / Messages)
        genOnlinePlayerData(); // In case of a reload caused by another plugin
        // GENERATION

        // EVENTS
        getServer().getPluginManager().registerEvents(new OnJoin(), this); // Add OnJoin Event
        getServer().getPluginManager().registerEvents(new OnQuit(), this); // Add OnQuit Event
        if (useAFKView) {
            getServer().getPluginManager().registerEvents(new OnPlayerMove(), this); // Add OnPlayerMove Event
        }
        if (useTeleportView) {
            getServer().getPluginManager().registerEvents(new OnTeleport(), this); // Add OnTeleport Event
        }
        // EVENTS

        // UPDATE CONFIG

        // COMMANDS
        Objects.requireNonNull(getCommand("view")).setExecutor(new ViewCommand()); // Executor for commands
        Objects.requireNonNull(getCommand("vdist")).setExecutor(new ViewCommand());
        Objects.requireNonNull(getCommand("vping")).setExecutor(new ViewCommand());
        Objects.requireNonNull(getCommand("view")).setTabCompleter(new OnTabComplete()); // Tab completer
        // COMMANDS

        //
        // Load & Get info
        //

        //
        // Schedule tasks
        //

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, applyViewDistance, 0L, this.getConfig().getInt("Delay.SetViewDelay", 5) * 20L); // CALCULATIONS SCHEDULER
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, detectAFK, 0L, this.getConfig().getInt("Delay.AFKDelay", 90) * 20L); // DETECT AFK SCHEDULER
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, detectFlying, 0L, this.getConfig().getInt("Delay.CheckFlyingDelay", 5) * 20L); // DETECT AFK SCHEDULER
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, calculations, 0L, this.getConfig().getInt("Delay.CalculationsDelay", 1) * 20L);

        //
        // BSTATS + ASYNC UPDATE CHECKER
        //

        //if (this.getConfig().getBoolean("Misc.Metrics")) {
        //noinspection unused
        final Metrics metrics = new Metrics(this); // METRICS
        //}
        if (this.getConfig().getBoolean("Misc.CheckUpdates")) {
            new AsyncUpdateChecker(this).checkForUpdate(); // Add AsyncUpdateChecker (Thx Benz56)
        }
    }

    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }

    // Calculations
    private final Runnable calculations = Calculations::calculatePlayersBestViewDistance;
    // Update Players View Distance
    private final Runnable applyViewDistance = Calculations::applyViewDistance;
    // Check if afk
    private final Runnable detectAFK = Checkers::AFKChecker;
    // Check if flying
    private final Runnable detectFlying = Checkers::flyingChecker;
}
