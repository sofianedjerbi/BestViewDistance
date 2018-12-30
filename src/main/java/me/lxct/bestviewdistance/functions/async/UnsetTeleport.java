package me.lxct.bestviewdistance.functions.async;

import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.onlinePlayers;

public class UnsetTeleport implements Runnable {
    private Player p;

    UnsetTeleport(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        onlinePlayers.get(p).setWaitingForTpUnset(false);
    }
}