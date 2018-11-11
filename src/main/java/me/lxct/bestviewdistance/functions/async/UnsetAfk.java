package me.lxct.bestviewdistance.functions.async;

import org.bukkit.event.player.PlayerMoveEvent;

import static me.lxct.bestviewdistance.functions.data.Variable.afkList;

public class UnsetAfk implements Runnable {
    private PlayerMoveEvent event;
    public UnsetAfk(PlayerMoveEvent event) {
        this.event = event;
    }

    @Override
    public void run() {
        afkList.remove(event.getPlayer().getName());
    }
}