package me.lxct.bestviewdistance.commands;

import me.lxct.bestviewdistance.functions.variable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.commands.commands.*;

public class viewCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("view") && sender.hasPermission("view.check")) {
            if(args.length == 0){
                commandHelp(sender);
            }
            else if (Bukkit.getServer().getPlayerExact(args[0]) != null) {
                commandView(args, sender);
            }
            else if(args[0].equalsIgnoreCase("server") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("tps") || args[0].equalsIgnoreCase("ping")) {
                commandServer(args, sender);
                commandPing(args, sender);
                commandReload(args, sender);
                commandTPS(args, sender);
            }
            else{
                sender.sendMessage(colorize("&cPlayer offline. /view <player>"));
            }
        }
        else if(cmd.getName().equalsIgnoreCase("view") && !sender.hasPermission("view.check")){
            if(sender instanceof Player){
                sender.sendMessage(colorize("&7View Distance => &d" + ((Player) sender).getViewDistance() + "/" + variable.max));
            }
        }
        return true;
    }
}
