package me.lxct.bestviewdistance.functions.async;

import me.lxct.bestviewdistance.event.OnTabComplete;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TabCompletions implements Runnable {
    private String[] args;
    public TabCompletions(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        ArrayList<String> COMMANDS = new ArrayList<>(Arrays.asList("tps", "server", "ping", "reload"));
        for (Player player : Bukkit.getOnlinePlayers()) {
            COMMANDS.add(player.getName());
        }
        StringUtil.copyPartialMatches(args[0], COMMANDS, OnTabComplete.completions);
        Collections.sort(OnTabComplete.completions); // Sort completions
    }
}