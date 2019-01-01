package me.lxct.bestviewdistance.functions.sync;

import org.bukkit.entity.Player;

public class SetViewDistance implements Runnable {
    private final int finalViewDistance;
    private final Player player;

    public SetViewDistance(final Player player, final int finalViewDistance) {
        this.player = player;
        this.finalViewDistance = finalViewDistance;
    }

    @Override
    public void run() {
        player.setViewDistance(finalViewDistance);
    }
}