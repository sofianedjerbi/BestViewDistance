package me.lxct.bestviewdistance.functions.sync;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.entity.Player;

public class SetAfkViewDistance implements Runnable {
    private Player player;
    public SetAfkViewDistance(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.setViewDistance(Variable.afk);
    }
}