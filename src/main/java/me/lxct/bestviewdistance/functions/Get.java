package me.lxct.bestviewdistance.functions;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.lxct.bestviewdistance.functions.packets.WrapperPlayClientSettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Get {
    // CALCULATE NEW REDUCTION INDICE
    public static double getNewReductionIndice(Double TPS) {
        if (TPS > Variable.tpslimit && TPS < 20) { // If tps > tps limit
            return Variable.reductionIndice - Variable.tpschange; // Decrease indice
        } else if (TPS < Variable.tpslimit) { // If tps < tps limit
            return Variable.reductionIndice + Variable.tpschange; // Increase indice
        } else {
            return Variable.reductionIndice; // Nothing.
        }
    }
    public static int getViewDistance(Player player){
        if (Bukkit.getVersion().contains("1.12")) {
            PacketContainer packetClientSettings = new PacketContainer(PacketType.Play.Client.SETTINGS);
            WrapperPlayClientSettings viewDistanceWrapper = new WrapperPlayClientSettings(packetClientSettings);
            viewDistanceWrapper.receivePacket(player);
            return viewDistanceWrapper.getViewDistance();
        }
        else{
            return player.getViewDistance();
        }
    }
}