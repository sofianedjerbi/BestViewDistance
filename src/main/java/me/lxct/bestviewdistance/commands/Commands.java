package me.lxct.bestviewdistance.commands;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.Other;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.Get.getPlayerConfig;
import static me.lxct.bestviewdistance.functions.Get.getServerConfig;
import static me.lxct.bestviewdistance.functions.Other.checkFile;
import static me.lxct.bestviewdistance.functions.Other.saveServerCustomViewFile;
import static me.lxct.bestviewdistance.functions.Set.*;
import static me.lxct.bestviewdistance.functions.data.Variable.*;


class Commands {


    static String colorize(String string) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', string);
    }

    static void commandTPS(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("tps")) {
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
        if (args[0].equalsIgnoreCase("ping")) {
            if (args.length != 2) {
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectPing)));
            } else {
                Player playerArgs = Bukkit.getServer().getPlayerExact(args[1]);
                if (playerArgs == null) {
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectPing)));
                } else {
                    player = playerArgs;
                    playerName = playerArgs.getName();
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewPing)));
                }
            }
        }
    }

    static void commandView(String[] args, CommandSender sender) {
        if (!args[0].equalsIgnoreCase("ping") && !args[0].equalsIgnoreCase("tps") && !args[0].equalsIgnoreCase("server") && !args[0].equalsIgnoreCase("reload")) {
            Player playerArgs = Bukkit.getServer().getPlayerExact(args[0]);
            if (playerArgs == null) {
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectView)));
            } else {
                player = playerArgs;
                playerName = playerArgs.getName();
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewPlayerLine1)));
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewPlayerLine2)));
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewPlayerLine3)));
            }
        }
    }

    static void commandSetServer(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("set")) {
            if (args.length != 3) {
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectSetView)));
            } else {
                try { // If args contain an int after player name
                    int view = Integer.parseInt(args[2]);
                    setCustomServerDataViewDistance(view);
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewSetServer)));
                } catch (NumberFormatException ex) {
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectSetView)));
                }
            }
        }
    }

    static void commandUnsetServer(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("unset")) {
            if (args.length != 2) {
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectUnsetView)));
            } else {
                if (serverViewSet) {
                    if (new File(BestViewDistance.plugin.getDataFolder() + "/data/", "server.yml").exists()) { // If the server has already a file
                        getServerConfig().set("Data.CustomViewDistanceIsSet", false); // Set value
                        saveServerCustomViewFile(); // Save the file
                    }
                    serverViewSet = false;  // Put inside data
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewUnsetServer)));
                } else {
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewNotSetServer)));
                }
            }
        }
    }

    static void commandSetPlayer(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("set")) {
            if (args.length != 3) {
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectSetView)));
            } else {
                Player playerArgs = Bukkit.getServer().getPlayerExact(args[1]);
                if (playerArgs == null) { // If player is offline / does not exist
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectSetView)));
                } else {
                    player = playerArgs;
                    playerName = playerArgs.getName();
                    try { // If args contain an int after player name
                        int view = Integer.parseInt(args[2]);
                        setCustomPlayerDataViewDistance(player, view);
                        sender.sendMessage(colorize(Other.replacePlaceHolders(viewSetPlayer)));
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectSetView)));
                    }
                }
            }
        }
        if (new File(BestViewDistance.plugin.getDataFolder(), player.getName() + ".yml").exists()) {
            getPlayerConfig(player).set("Data.CustomViewDistance", args[1]);
        }
    }

    static void commandUnsetPlayer(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("unset")) {
            if (args.length != 2) {
                sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectUnsetView)));
            } else {
                playerName = args[1];
                if (playerName == null) { // If args 1 is empty or wrong
                    sender.sendMessage(colorize(Other.replacePlaceHolders(viewIncorrectUnsetView)));
                } else {
                    player = null; // Cannot use %PING% and so on here.
                    if (checkFile(new File(BestViewDistance.plugin.getDataFolder() + "/data/"), playerName)) { // If player has config file
                        if (playerViewSet.containsKey(playerName)) { // If player online
                            playerViewSet.remove(player.getName());
                        }
                        File file = new File(BestViewDistance.plugin.getDataFolder() + "/data/", playerName + ".yml");
                        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(file);
                        if (userConfig.getBoolean("Data.CustomViewDistanceIsSet")) {
                            userConfig.set("Data.CustomViewDistanceIsSet", false); // Set boolean false
                            sender.sendMessage(colorize(Other.replacePlaceHolders(viewUnsetPlayer)));
                        } else {
                            sender.sendMessage(colorize(Other.replacePlaceHolders(viewNotSetPlayer)));
                        }
                        try {
                            userConfig.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        sender.sendMessage(colorize(Other.replacePlaceHolders(viewNotSetPlayer)));
                    }
                }
            }
        }
    }

    // IN CASE OF A /CLEAR COMMAND FOR CLEARING ALL CUSTOM VIEW DISTANCES
//    static void commandClearPlayer(String[] args, CommandSender sender) {
//        if (args[0].equalsIgnoreCase("clear")) {
//            for(File file : Objects.requireNonNull(new File(BestViewDistance.plugin.getDataFolder() + "/data/").listFiles())){
//                YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(file);
//                userConfig.set("Data.CustomViewDistanceIsSet", false); // Set boolean false
//            }
//            playerViewSet.clear();
//        }
//    }

    static void commandServer(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("server")) {
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewServer)));
        }
    }

    static void commandReload(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("reload")) {
            BestViewDistance.plugin.reloadConfig();
            Other.loadMessagesYml(); // Like a "BestViewDistance.plugin.reloadCustomConfig();" you know
            loadVariables();
            sender.sendMessage(colorize(Other.replacePlaceHolders(viewReload)));
        }
    }
}