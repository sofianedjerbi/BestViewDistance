package me.lxct.bestviewdistance.functions.async;

import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.onlinePlayers;

public class UnsetTeleport implements Runnable {
    private final Player p;

    UnsetTeleport(final Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        onlinePlayers.get(p).setWaitingForTpUnset(false);
    }
}