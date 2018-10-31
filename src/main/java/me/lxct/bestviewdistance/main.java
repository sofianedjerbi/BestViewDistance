package me.lxct.bestviewdistance;

import static me.lxct.bestviewdistance.functions.get.*;
import static me.lxct.bestviewdistance.functions.gen.*;
import static me.lxct.bestviewdistance.functions.set.*;
import static me.lxct.bestviewdistance.commands.commands.*;

import me.lxct.bestviewdistance.event.onLogin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class main extends org.bukkit.plugin.java.JavaPlugin
{
    public static main plugin;

    @Override
    public void onEnable() {
        plugin=this;
        getServer().getPluginManager().registerEvents(new onLogin(), this);
        // GENERATE CONFIG
        this.saveDefaultConfig();
        // WARNING
        Bukkit.getLogger().info("╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐");
        Bukkit.getLogger().info("╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤ ");
        Bukkit.getLogger().info("╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘");
        Bukkit.getLogger().info("╚ Make sure you use this plugin with paper.");
        Bukkit.getLogger().info("╚ https://papermc.io/");
        Bukkit.getLogger().info("╚ Best View Distance, By Lxct.");
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