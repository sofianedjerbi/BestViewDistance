package me.lxct.bestviewdistance.functions.async;

import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.waitForTPUnset;

public class UnsetTeleport implements Runnable {
    private Player player;

    UnsetTeleport(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        waitForTPUnset.remove(player.getName());
    }
}