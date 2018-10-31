package me.lxct.bestviewdistance.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
        return true;
    }
}
