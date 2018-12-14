package me.lxct.bestviewdistance.functions.hooks;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.plugin.Plugin;

class ProtocolLibHook {
    static void protocolLibHook(Plugin plugin) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(plugin,
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
