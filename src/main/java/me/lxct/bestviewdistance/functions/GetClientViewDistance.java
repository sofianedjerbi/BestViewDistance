package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.packets.AbstractPacket;
import me.lxct.bestviewdistance.functions.packets.WrapperPlayClientSettings;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.packets.WrapperPlayClientSettings.getViewDistanceOld;

class GetClientViewDistance implements Runnable {
    private Player player;
    static int getViewDistanceOld;

    GetClientViewDistance(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        AbstractPacket.receivePacket(player);
        new WrapperPlayClientSettings(AbstractPacket.getHandle());
        int getViewDistanceOld = getViewDistanceOld();
    }
}
