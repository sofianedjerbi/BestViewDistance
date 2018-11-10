package me.lxct.bestviewdistance.functions.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class WrapperPlayClientSettings extends AbstractPacket {
    private static final PacketType TYPE = PacketType.Play.Client.SETTINGS;

    public WrapperPlayClientSettings() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayClientSettings(PacketContainer packet) {
        super(packet, TYPE);
    }


    /**
     * Retrieve View distance.
     * <p>
     * Notes: client-side render distance(chunks)
     *
     * @return The current View distance
     */
    public int getViewDistance() {
        return handle.getIntegers().read(0);
    }
}
