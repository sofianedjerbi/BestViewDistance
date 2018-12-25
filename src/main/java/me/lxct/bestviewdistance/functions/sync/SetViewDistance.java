package me.lxct.bestviewdistance.functions.sync;

import org.bukkit.entity.Player;

public class SetViewDistance implements Runnable {
    private int finalViewDistance;
    private Player player;

    public SetViewDistance(Player player, int finalViewDistance) {
        this.player = player;
        this.finalViewDistance = finalViewDistance;
    }

    @Override
    public void run() {
        player.setViewDistance(finalViewDistance);
    }
}