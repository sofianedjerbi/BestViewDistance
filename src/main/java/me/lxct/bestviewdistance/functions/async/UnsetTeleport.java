package me.lxct.bestviewdistance.functions.async;

import org.bukkit.entity.Player;

import me.lxct.bestviewdistance.functions.BVDPlayer;

import static me.lxct.bestviewdistance.functions.data.Variable.onlinePlayers;

public class UnsetTeleport implements Runnable {
    private final Player p;

    UnsetTeleport(final Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        if (p == null) {
            return;
        }

        BVDPlayer pl = onlinePlayers.get(p);
        if (pl == null) {
            return;
        }

        pl.setWaitingForTpUnset(false);
    }
}