package me.lxct.bestviewdistance.functions;

import org.bukkit.entity.Player;

class SetAfkViewDistance implements Runnable {
    private Player player;
    SetAfkViewDistance(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.setViewDistance(Variable.afk);
    }
}