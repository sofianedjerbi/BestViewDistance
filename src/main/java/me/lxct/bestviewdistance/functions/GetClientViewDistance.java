package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.packets.WrapperPlayClientSettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class GetClientViewDistance implements Runnable {
    private Player player;


    GetClientViewDistance(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        WrapperPlayClientSettings viewDistanceWrapper = new WrapperPlayClientSettings();
        viewDistanceWrapper.receivePacket(player);
        Bukkit.broadcastMessage(String.valueOf(viewDistanceWrapper.getViewDistance()));
        Variable.tmpClientViewDistance = viewDistanceWrapper.getViewDistance();
    }
}
