package me.lxct.bestviewdistance.functions.async;

import org.bukkit.event.player.PlayerMoveEvent;

import me.lxct.bestviewdistance.functions.BVDPlayer;

import static me.lxct.bestviewdistance.functions.data.Variable.onlinePlayers;

public class UnsetAfk implements Runnable {
    private final PlayerMoveEvent e;

    public UnsetAfk(final PlayerMoveEvent e) {
        this.e = e;
    }

    @Override
    public void run() {
        // Since this is an async task, the player could have logged off
        // this usually happens if they lag or the server lags
        if (e.getPlayer() == null) {
            return;
        }

        BVDPlayer p = onlinePlayers.get(e.getPlayer());
        if (p == null) {
            return;
        }

        p.setAfk(false);
    }
}