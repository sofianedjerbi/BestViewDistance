package me.lxct.bestviewdistance.event;

import me.lxct.bestviewdistance.functions.async.TabCompletions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class OnTabComplete implements TabCompleter {

    public static ArrayList<String> completions;

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        new TabCompletions(args);
        return completions;
    }
}