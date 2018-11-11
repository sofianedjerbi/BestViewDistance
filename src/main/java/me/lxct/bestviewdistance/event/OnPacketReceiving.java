package me.lxct.bestviewdistance.event;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.event.Listener;

import static me.lxct.bestviewdistance.BestViewDistance.protocolManager;

public class OnPacketReceiving implements Listener {
    public OnPacketReceiving(){
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
