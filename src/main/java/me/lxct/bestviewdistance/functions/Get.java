package me.lxct.bestviewdistance.functions;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class Get extends org.bukkit.plugin.java.JavaPlugin {
    private static PacketContainer packet;
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
            try {
                ProtocolLibrary.getProtocolManager().recieveClientPacket(player, PacketContainer.fromPacket(PacketType.Play.Client.SETTINGS));
                return packet.getIntegers().read(0); // Get View Distance

            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace(); // ERRORS
                return 0;
            }
        }
        else{
            return player.getViewDistance();
        }
    }
}