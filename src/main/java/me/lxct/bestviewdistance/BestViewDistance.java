package me.lxct.bestviewdistance;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.lxct.bestviewdistance.commands.ViewCommand;
import me.lxct.bestviewdistance.event.*;
import me.lxct.bestviewdistance.functions.Other;
import me.lxct.bestviewdistance.functions.Set;
import me.lxct.bestviewdistance.functions.async.AsyncUpdateChecker;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

import static me.lxct.bestviewdistance.functions.Get.*;
import static me.lxct.bestviewdistance.functions.Other.*;
import static me.lxct.bestviewdistance.functions.Set.calculatePlayersBestViewDistance;
import static me.lxct.bestviewdistance.functions.Set.setServerLimits;
import static me.lxct.bestviewdistance.functions.data.Variable.loadVariables;

public class BestViewDistance extends JavaPlugin {

    public static BestViewDistance plugin;

    @Override
    public void onEnable() {
        plugin = this; // Allow BestViewDistance.plugin
        // WARNING
        Bukkit.getLogger().info("╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐"); // Display
        Bukkit.getLogger().info("╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤ ");
        Bukkit.getLogger().info("╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘");
        Bukkit.getLogger().info("╚ Make sure you use this plugin with Paper.");
        Bukkit.getLogger().info("╚ https://papermc.io/");
        Bukkit.getLogger().info("╚ Best View Distance, By Lxct. ");
        // WARNING

        //
        // Retro compatibility
        //

        if (Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.11") || Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.9") || Bukkit.getVersion().contains("1.8")) { // Add 1.12 Support for Client View Distance
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            protocolManager.addPacketListener(new PacketAdapter(BestViewDistance.plugin,
                    ListenerPriority.NORMAL,
                    PacketType.Play.Client.SETTINGS) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    if (event.getPacketType() == PacketType.Play.Client.SETTINGS) {
                        PacketContainer packet = event.getPacket();
                        Variable.playerSettingsViewDistance.put(event.getPlayer().getName(), packet.getIntegers().read(0));
                    }
                }
            });
        }

        if (Bukkit.getVersion().contains("1.8")) {
            try {
                serverInstance = getNMSClass("MinecraftServer").getMethod("getServer").invoke(null);
                tpsField = serverInstance.getClass().getField("recentTps");
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        //
        // Retro compatibility
        //

        //
        // Load & Get info
        //

        getServer().getPluginManager().registerEvents(new OnJoin(), this); // Add OnLogin Event
        getServer().getPluginManager().registerEvents(new OnLogin(), this); // Add OnLogin Event
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this); // Add OnPlayerMove Event
        saveDefaultConfig(); // GENERATE
        genMessagesYml(); // Generate Messages.yml
        loadMessagesYml(); // Load CustomConfig (Messages)
        loadVariables(); // Load Variables (Config / Messages)
        genOnlinePlayerData(); // In case of a reload caused by another plugin
        getCommand("view").setExecutor(new ViewCommand()); // Executor for commands
        getCommand("vdist").setExecutor(new ViewCommand());
        getCommand("vping").setExecutor(new ViewCommand());
        getCommand("view").setTabCompleter(new OnTabComplete()); // Tab completer

        //
        // Load & Get info
        //

        //
        // Schedule tasks
        //

        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, applyViewDistance, 0L, this.getConfig().getInt("ViewDistance.SetViewDelay") * 20L); // CALCULATIONS SCHEDULER
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, detectAFK, 0L, this.getConfig().getInt("Performances.AFKTimer") * 20L); // DETECT AFK SCHEDULER
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, calculations, 0L, this.getConfig().getInt("ViewDistance.CalculationsDelay") * 20L);

        //
        // BSTATS + ASYNC UPDATE CHECKER
        //

        //if (this.getConfig().getBoolean("Other.Metrics")) {
        //noinspection unused
        Metrics metrics = new Metrics(this); // METRICS
        //}
        if (this.getConfig().getBoolean("Other.CheckUpdates")) {
            new AsyncUpdateChecker(this).checkForUpdate(); // Add AsyncUpdateChecker (Thx Benz56)
        }
    }

    private Runnable calculations = // CALCULATIONS
            () -> {
                Variable.reductionIndice = getNewReductionIndice(get1minTPS()); // Update Reduction Indice
                setServerLimits(); // Control
                calculatePlayersBestViewDistance(Variable.reductionIndice); // Update Players View Distance
            };

    // Update Players View Distance
    private Runnable applyViewDistance = // CALCULATIONS
            Set::applyViewDistance;

    private Runnable detectAFK = // CHECK IF AFK
            Other::putPlayerAFK;
}