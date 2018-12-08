package me.lxct.bestviewdistance.functions.sync;

import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.waitForTPUnset;

public class UnsetTeleportViewDistance implements Runnable {
    private final int finalViewDistance;
    private Player player;

    public UnsetTeleportViewDistance(Player player, int finalViewDistance) {
        this.player = player;
        this.finalViewDistance = finalViewDistance;
    }

    @Override
    public void run() {
        player.setViewDistance(finalViewDistance);
        waitForTPUnset.remove(player.getName());
    }
}