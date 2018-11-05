package me.lxct.bestviewdistance.functions;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.google.common.base.Objects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class Get extends org.bukkit.plugin.java.JavaPlugin {
    private static PacketContainer handle;

    protected Get(PacketContainer handle, PacketType type) {
        // Make sure we're given a valid packet
        if (handle == null)
            throw new IllegalArgumentException("Packet handle cannot be NULL.");
        if (!Objects.equal(handle.getType(), type))
            throw new IllegalArgumentException(handle.getHandle()
                    + " is not a packet of type " + type);

        Get.handle = handle;
    }


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
                return handle.getIntegers().read(0); // Get View Distance

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