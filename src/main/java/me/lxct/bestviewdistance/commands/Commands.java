package me.lxct.bestviewdistance.commands;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.Other;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.*;


class Commands {


    static String colorize(String string) {return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);}

    static void commandTPS(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("tps")){
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewTps)));
        }
    }

    static void commandHelp(CommandSender sender) {
        sender.sendMessage(colorize(Other.replacePlaceHolders(viewHelpLine1)));
        sender.sendMessage(colorize(Other.replacePlaceHolders(viewHelpLine2)));
        sender.sendMessage(colorize(Other.replacePlaceHolders(viewHelpLine3)));
        sender.sendMessage(colorize(Other.replacePlaceHolders(viewHelpLine4)));
        sender.sendMessage(colorize(Other.replacePlaceHolders(viewHelpLine5)));
        sender.sendMessage(colorize(Other.replacePlaceHolders(viewHelpLine6)));
        sender.sendMessage(colorize(Other.replacePlaceHolders(viewHelpLine7)));
    }

    static void commandPing(String[] args, CommandSender sender) {
        if(args[0].equalsIgnoreCase("ping")) {
            Player playerArgs = Bukkit.getServer().getPlayerExact(args[1]);
            if (playerArgs == null) {
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectPing)));
            } else {
                player = playerArgs;
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewPing)));
            }
        }
    }

    static void commandView(String[] args, CommandSender sender) {
        Player playerArgs = Bukkit.getServer().getPlayerExact(args[0]);
        if (playerArgs == null) {
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectView)));
        }
        else {
            player = playerArgs;
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewPlayerLine1)));
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewPlayerLine2)));
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewPlayerLine3)));
        }
    }


    static void commandServer(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("server")){
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewServer)));
        }
    }

    static void commandReload(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("reload")){
            BestViewDistance.plugin.reloadConfig();
            Other.loadMessagesYml(); // Like a "BestViewDistance.plugin.reloadCustomConfig();" you know
            loadVariables();
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewReload)));
        }
    }


}