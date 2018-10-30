package me.lxct.bestviewdistance;

import static me.lxct.bestviewdistance.functions.get.*;
import static me.lxct.bestviewdistance.functions.gen.*;
import static me.lxct.bestviewdistance.functions.set.*;
import static me.lxct.bestviewdistance.commands.commands.*;

import me.lxct.bestviewdistance.event.onLogin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class main extends org.bukkit.plugin.java.JavaPlugin
{

    public static main plugin;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new onLogin(), this);
        // GENERATE CONFIG
        plugin.saveDefaultConfig();
        // WARNING
        Bukkit.getLogger().info("[BestViewDistance] -------------------------------------------------");
        Bukkit.getLogger().info("[BestViewDistance] Best View Distance By LXCT => WARNING :");
        Bukkit.getLogger().info("[BestViewDistance] Make sure you use this plugin with Paper !");
        Bukkit.getLogger().info("[BestViewDistance] https://papermc.io/ <3");
        Bukkit.getLogger().info("[BestViewDistance] -------------------------------------------------");
        // WARNING
        genFolders(); // CREATING /plugins/BestViewDistance/data/
        genServerData(); // CREATING SERVER.YML
        genConfig(); // CREATING CONFIG.YML
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, task, 0L,this.getConfig().getInt("ViewDistance.Delay")*20L); // SCHEDULER
    }

    private Runnable task =
            () -> {
                setServerReductionIndice(getNewReductionIndice(Bukkit.getTPS()[0])); // Update Reduction Indice
                setServerLimits(); // Control
                setPlayersBestViewDistance(getActualReductionIndice()); // Update Players View Distance
            };



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("view") && sender.hasPermission("view.check")) {
            if (args[0].equalsIgnoreCase("server") || args[0].equalsIgnoreCase("tps") || args[0].equalsIgnoreCase("ping") || args[0].equalsIgnoreCase("limit"))  {
                commandServer(args, sender);
                commandPing(args, sender);
                commandLimit(args, sender);
                commandTPS(args, sender);
            }
            else if (args[0].equalsIgnoreCase("reload")){
                this.reloadConfig();
                sender.sendMessage(colorize("&aBestViewDistance config reloaded !"));
            }
            else {commandView(args, sender);}
        }
        return true;
    }
}