package me.lxct.bestviewdistance.commands;

import me.lxct.bestviewdistance.main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.get.getActualReductionIndice;
import static me.lxct.bestviewdistance.functions.get.getPlayerViewDistance;

class commands {


    private static String colorize(String string) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
    }

    static void commandTPS(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("tps")){
            sender.sendMessage(colorize("&aTPS => &d" + Bukkit.getTPS()[0]));
        }
    }

    static void commandHelp(CommandSender sender) {
        sender.sendMessage(colorize("/view server => Get reduction indice."));
        sender.sendMessage(colorize("/view tps => Get server's tps."));
        sender.sendMessage(colorize("/view ping <player> => Get player ping."));
        sender.sendMessage(colorize("/view <player> => Get player actual view distance/max view distance."));
        sender.sendMessage(colorize("/view => This message."));
    }

    static void commandPing(String[] args, CommandSender sender) {
        if(args[0].equalsIgnoreCase("ping")) {
            Player player = Bukkit.getServer().getPlayerExact(args[1]);
            if (player == null) {
                sender.sendMessage(colorize("&c/view ping <player>"));
            } else {
                sender.sendMessage(colorize("&aPing of " + args[1] + " => &d" + player.spigot().getPing() + "ms"));
            }
        }
    }

    static void commandView(String[] args, CommandSender sender) {
        Player player = Bukkit.getServer().getPlayerExact(args[0]);
        if (player == null) {
            sender.sendMessage(colorize("&c/view <player>"));
        }
        else {
            String TpsMsg = String.valueOf(player.getViewDistance());
            sender.sendMessage(colorize("&aView Distance of " + player.getName() + " => &d" + TpsMsg + "/" + getPlayerViewDistance(player)));
        }
    }


    static void commandServer(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("server")){
            String indice = String.valueOf(getActualReductionIndice()*100);
            sender.sendMessage(colorize("&aThe view distance is reduced by &d" + indice + "%"));
        }
    }

    static void commandReload(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("reload")){
            main.plugin.reloadConfig();
            sender.sendMessage(colorize("&aBest View Distance config reloaded !"));
        }
    }


}