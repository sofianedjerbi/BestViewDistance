package me.lxct.bestviewdistance.commands;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Get.getViewDistance;
import static me.lxct.bestviewdistance.functions.Variable.*;


class Commands {


    static String colorize(String string) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
    }

    static void commandTPS(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("tps")){
            sender.sendMessage(colorize("&aTPS => &d" + Bukkit.getTPS()[0]));
        }
    }

    static void commandHelp(CommandSender sender) {
        sender.sendMessage(colorize("&d/view server => &aGet reduction indice."));
        sender.sendMessage(colorize("&d/view tps => &aGet server's tps."));
        sender.sendMessage(colorize("&d/view ping <player> => &aGet player ping."));
        sender.sendMessage(colorize("&d/view <player> => &aGet player view distance/max view distance."));
        sender.sendMessage(colorize("&d/view => &aThis message."));
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
            sender.sendMessage(colorize("&aView Distance of " + player.getName() + " => &d" + String.valueOf(player.getViewDistance()) + "/" + getViewDistance(player)));
        }
    }


    static void commandServer(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("server")){
            String indice = String.valueOf(reductionIndice*100);
            sender.sendMessage(colorize("&aThe view distance is reduced by &d" + indice + "%"));
        }
    }

    static void commandReload(String[] args, CommandSender sender){
        if(args[0].equalsIgnoreCase("reload")){
            BestViewDistance.plugin.reloadConfig();
            max = BestViewDistance.plugin.getConfig().getInt("ViewDistance.Max");
            afk = BestViewDistance.plugin.getConfig().getInt("ViewDistance.AFK");
            min = BestViewDistance.plugin.getConfig().getInt("ViewDistance.Min");
            rping = BestViewDistance.plugin.getConfig().getInt("Performances.PingForReduction");
            aping = BestViewDistance.plugin.getConfig().getInt("Performances.PingForAugmentation");
            tpslimit = BestViewDistance.plugin.getConfig().getDouble("Performances.TPSLimit");
            tpschange = BestViewDistance.plugin.getConfig().getDouble("Performances.TPSChangeIndice");
            maxindice = BestViewDistance.plugin.getConfig().getDouble("Performances.MaxReductionIndice");
            sender.sendMessage(colorize("&aBest View Distance config reloaded !"));
        }
    }


}