package me.lxct.bestviewdistance.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.commands.Commands.*;
import static me.lxct.bestviewdistance.functions.Variable.playerViewDistance;

public class ViewCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("view")) {
            if (args.length == 0) {
                commandHelp(sender);
            } else if (Bukkit.getServer().getPlayerExact(args[0]) != null && sender.hasPermission("view.check")) {
                commandView(args, sender);
            } else if (args[0].equalsIgnoreCase("server") || args[0].equalsIgnoreCase("tps") || args[0].equalsIgnoreCase("ping") && sender.hasPermission("view.check")) {
                commandServer(args, sender);
                commandPing(args, sender);
                commandTPS(args, sender);
            } else if (sender.hasPermission("view.reload")) {
                commandReload(args, sender);
            } else {
                sender.sendMessage(colorize("&cPlayer offline. /view <player>"));
            }
        }
        else if (cmd.getName().equalsIgnoreCase("vdist") && sender.hasPermission("view.info")) {
            if (sender instanceof Player) {
                sender.sendMessage(colorize("&7View Distance => &d" + ((Player) sender).getViewDistance() + "/" + playerViewDistance.get(sender.getName())));
            }
        }
        return true;
    }
}