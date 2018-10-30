package me.lxct.bestviewdistance;

import static me.lxct.bestviewdistance.functions.get.*;
import static me.lxct.bestviewdistance.functions.gen.*;
import static me.lxct.bestviewdistance.functions.set.*;
import static me.lxct.bestviewdistance.commands.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
        genFolders(); // CREATING /plugins/BestViewDistance/data/
        genServerData(); // CREATING SERVER.YML
        genConfig(); // CREATING CONFIG.YML
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, task, 0L,getDelayViewDistance()*20L); // SCHEDULER
    }

    private Runnable task =
            () -> {
                double tps = Bukkit.getTPS()[0]; // Get TPS
                setServerReductionIndice(getNewReductionIndice(tps)); // Update Reduction Indice
                setPlayersBestViewDistance(getNewReductionIndice(tps)); // Update Players View Distance
            };

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("view")) {
            if (args[0].equalsIgnoreCase("server") || args[0].equalsIgnoreCase("tps")) {
                commandServer(args, sender);

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