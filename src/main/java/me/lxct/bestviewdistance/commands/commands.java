package me.lxct.bestviewdistance.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.get.*;

public class commands extends org.bukkit.plugin.java.JavaPlugin {


    public static String colorize(String string) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void commandTPS(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("tps")){
            sender.sendMessage(colorize("&aTPS => &d" + Bukkit.getTPS()[0]));
        }
    }

    public static void commandLimit(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("limit")){
            sender.sendMessage(colorize("&aMax => &d" + getMaxViewDistance()));
            sender.sendMessage(colorize("&aMin => &d" + getMinViewDistance()));
        }
    }

    public static void commandPing(String[] args, CommandSender sender) {
        if(args[0].equalsIgnoreCase("ping")) {
            Player player = Bukkit.getServer().getPlayerExact(args[1]);
            if (player == null) {
                sender.sendMessage(colorize("&c/view ping <player>"));
            } else {
                sender.sendMessage(colorize("&aPing of " + args[1] + " => &d" + player.spigot().getPing() + "ms"));
            }
        }
    }

    public static void commandView(String[] args, CommandSender sender) {
        Player player = Bukkit.getServer().getPlayerExact(args[0]);
        if (player == null) {
            sender.sendMessage(colorize("&c/view <player>"));
        }
        else {
            String TpsMsg = String.valueOf(player.getViewDistance());
            sender.sendMessage(colorize("&aView Distance of " + player.getName() + " => &d" + TpsMsg + "/" + getPlayerViewDistance(player)));
        }
    }


    public static void commandServer(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("server")){
            String indice = String.valueOf(getActualReductionIndice()*100);
            sender.sendMessage(colorize("&aThe view distance is reduced by &d" + indice + "%"));
        }
    }
}