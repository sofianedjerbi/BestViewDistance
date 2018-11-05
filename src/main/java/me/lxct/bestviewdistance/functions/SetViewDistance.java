package me.lxct.bestviewdistance.functions;

import org.bukkit.entity.Player;

class SetViewDistance implements Runnable {
    private final int finalViewDistance;
    private Player player;
    SetViewDistance(Player player, int finalViewDistance) {
        this.player = player;
        this.finalViewDistance = finalViewDistance;
    }

    @Override
    public void run() {
        player.setViewDistance(finalViewDistance);
    }
}