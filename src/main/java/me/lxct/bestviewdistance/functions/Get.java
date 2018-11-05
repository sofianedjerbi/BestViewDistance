package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.packets.AbstractPacket;
import me.lxct.bestviewdistance.functions.packets.WrapperPlayClientSettings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Get extends org.bukkit.plugin.java.JavaPlugin {
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
            AbstractPacket.receivePacket(player);
            new WrapperPlayClientSettings(AbstractPacket.getHandle());
            return WrapperPlayClientSettings.getViewDistanceOld();
        }
        else{
            return player.getViewDistance();
        }
    }
}