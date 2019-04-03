package me.lxct.bestviewdistance.commands;

import me.lxct.bestviewdistance.functions.util.Misc;
import me.lxct.bestviewdistance.functions.data.Variable;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.commands.Commands.*;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class ViewCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Sadly, this requires a player.");
            return true;
        }

        playerData = onlinePlayers.get(sender);

        if (cmd.getName().equalsIgnoreCase("view")) {
            if (args.length == 0) {
                commandHelp(sender);
            } else {
                if (sender.hasPermission("view.check")) {
                    commandServer(args, sender);
                    commandTPS(args, sender);
                    commandPing(args, sender);
                    commandView(args, sender);
                }

                if (sender.hasPermission("view.reload")) {
                    commandReload(args, sender);
                }
            }
        } else if (cmd.getName().equalsIgnoreCase("vdist") && sender.hasPermission("view.info")) {
            sender.sendMessage(colorize(Misc.replacePlaceHolders(vdistLine1)));
            sender.sendMessage(colorize(Misc.replacePlaceHolders(vdistLine2)));
            sender.sendMessage(colorize(Misc.replacePlaceHolders(vdistLine3)));

            if (!Variable.hideVdistLine4) {
                sender.sendMessage(colorize(Misc.replacePlaceHolders(vdistLine4)));
            }
        } else if (cmd.getName().equalsIgnoreCase("vping") && sender.hasPermission("view.info")) {
            sender.sendMessage(colorize(Misc.replacePlaceHolders(vping)));
        }

        return true;
    }
}
