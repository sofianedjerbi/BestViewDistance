package me.lxct.bestviewdistance.functions;

import com.comphenix.protocol.events.PacketContainer;
import me.lxct.bestviewdistance.functions.packets.WrapperPlayClientSettings;
import org.bukkit.entity.Player;

class GetClientViewDistance implements Runnable {
    private Player player;
    private PacketContainer packetClientSettings;


    GetClientViewDistance(Player player) {
        this.player = player;
    }

    @Override
    public void run() {

        WrapperPlayClientSettings viewDistanceWrapper = new WrapperPlayClientSettings(packetClientSettings);
        viewDistanceWrapper.receivePacket(player);
        int oldClientViewDistance = viewDistanceWrapper.getViewDistance();
    }
}
