package me.lxct.bestviewdistance.commands;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.util.Misc;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.*;


public class Commands {


    public static String colorize(String string) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
    }

    static void commandTPS(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("tps")) {
            sender.sendMessage(colorize(Misc.replacePlaceHolders(viewTps)));
        }
    }

    static void commandHelp(CommandSender sender) {
        sender.sendMessage(colorize(Misc.replacePlaceHolders(viewHelpLine1)));
        sender.sendMessage(colorize(Misc.replacePlaceHolders(viewHelpLine2)));
        sender.sendMessage(colorize(Misc.replacePlaceHolders(viewHelpLine3)));
        sender.sendMessage(colorize(Misc.replacePlaceHolders(viewHelpLine4)));
        sender.sendMessage(colorize(Misc.replacePlaceHolders(viewHelpLine5)));
        sender.sendMessage(colorize(Misc.replacePlaceHolders(viewHelpLine6)));
        sender.sendMessage(colorize(Misc.replacePlaceHolders(viewHelpLine7)));
    }

    static void commandPing(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("ping")) {
            if (args.length != 2) {
                sender.sendMessage(colorize(Misc.replacePlaceHolders(viewIncorrectPing)));
            } else {
                Player p = Bukkit.getServer().getPlayerExact(args[1]);
                if (p == null) {
                    sender.sendMessage(colorize(Misc.replacePlaceHolders(viewIncorrectPing)));
                } else {
                    playerData = onlinePlayers.get(p);
                    sender.sendMessage(colorize(Misc.replacePlaceHolders(viewPing)));
                }
            }
        }
    }

    static void commandView(String[] args, CommandSender sender) {
        if (!args[0].equalsIgnoreCase("ping") && !args[0].equalsIgnoreCase("tps") && !args[0].equalsIgnoreCase("set") && !args[0].equalsIgnoreCase("unset") && !args[0].equalsIgnoreCase("server") && !args[0].equalsIgnoreCase("reload")) {
            Player p = Bukkit.getServer().getPlayerExact(args[0]);
            if (p == null) {
                sender.sendMessage(colorize(Misc.replacePlaceHolders(viewIncorrectView)));
            } else {
                playerData = onlinePlayers.get(p);
                sender.sendMessage(colorize(Misc.replacePlaceHolders(viewPlayerLine1)));
                sender.sendMessage(colorize(Misc.replacePlaceHolders(viewPlayerLine2)));
                sender.sendMessage(colorize(Misc.replacePlaceHolders(viewPlayerLine3)));
            }
        }
    }

    static void commandServer(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("server")) {
            sender.sendMessage(colorize(Misc.replacePlaceHolders(viewServer)));
        }
    }

    static void commandReload(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("reload")) {
            BestViewDistance.plugin.reloadConfig();
            loadVariables();
            sender.sendMessage(colorize(Misc.replacePlaceHolders(viewReload)));
        }
    }
}