package me.lxct.bestviewdistance.functions.async;

import org.bukkit.event.player.PlayerMoveEvent;

import static me.lxct.bestviewdistance.functions.data.Variable.onlinePlayers;

public class UnsetAfk implements Runnable {
    private PlayerMoveEvent e;

    public UnsetAfk(PlayerMoveEvent e) {
        this.e = e;
    }

    @Override
    public void run() {
        onlinePlayers.get(e.getPlayer()).setAfk(false);
    }
}