package me.lxct.bestviewdistance.functions.data;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.lxct.bestviewdistance.BestViewDistance;

public class Hooks {
    public static void protocolLibHook(BestViewDistance plugin) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(BestViewDistance.plugin,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.SETTINGS) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Client.SETTINGS) {
                    PacketContainer packet = event.getPacket();
                    Variable.playerSettingsViewDistance.put(event.getPlayer().getName(), packet.getIntegers().read(0));
                }
            }
        });
    }
}
