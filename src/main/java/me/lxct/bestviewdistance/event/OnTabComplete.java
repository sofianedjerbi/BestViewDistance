package me.lxct.bestviewdistance.event;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OnTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args[0].equalsIgnoreCase("ping")) { // Only for /view ping <player>
            if (args.length <= 2) {
                ArrayList<String> COMMANDS = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    COMMANDS.add(player.getName());
                }
                ArrayList<String> completions = new ArrayList<>(COMMANDS.size());
                if (args.length == 2) {
                    StringUtil.copyPartialMatches(args[1], COMMANDS, completions);
                }
                Collections.sort(completions); // Sort completions
                return completions;
            } else {
                return new ArrayList<>();
            }
        } else if (args.length > 1) { // Does not spam tab complete
            return new ArrayList<>();
        } else { // TPS / SERVER / PING / RELOAD completion
            ArrayList<String> COMMANDS = new ArrayList<>(Arrays.asList("tps", "server", "ping", "reload", "ver"));
            for (Player player : Bukkit.getOnlinePlayers()) {
                COMMANDS.add(player.getName());
            }
            ArrayList<String> completions = new ArrayList<>(COMMANDS.size());
            StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
            Collections.sort(completions); // Sort completions
            return completions;
        }
    }
}