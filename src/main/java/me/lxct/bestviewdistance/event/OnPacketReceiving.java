package me.lxct.bestviewdistance.event;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnPacketReceiving implements Listener {
    @EventHandler
    public void onPacketReceiving(PacketEvent e) {
        PacketContainer wrapper = e.getPacket();
        if ((e.getPacket().getType() == PacketType.Play.Client.SETTINGS)) {
            Variable.playerSettingsViewDistance.put(e.getPlayer().getName(), wrapper.getIntegers().readSafely(0));
        }
    }
}

