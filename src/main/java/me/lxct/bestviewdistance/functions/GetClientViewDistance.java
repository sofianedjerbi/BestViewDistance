package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Variable;
import me.lxct.bestviewdistance.functions.packets.WrapperPlayClientSettings;
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
        Variable.tmpClientViewDistance = viewDistanceWrapper.getViewDistance();
    }
}
