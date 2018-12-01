package me.lxct.bestviewdistance.functions.sync;

import org.bukkit.entity.Player;

public class SetAfkViewDistance implements Runnable {
    private Player player;
    private int permissionViewDistance;

    public SetAfkViewDistance(Player player, int permissionViewDistance) {
        this.player = player;
        this.permissionViewDistance = permissionViewDistance;
    }

    @Override
    public void run() {
        player.setViewDistance(permissionViewDistance);
    }
}