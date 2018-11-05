package me.lxct.bestviewdistance.functions.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class WrapperPlayClientSettings extends AbstractPacket {
    private static final PacketType TYPE = PacketType.Play.Client.SETTINGS;

    //public WrapperPlayClientSettings() {
    //    super(new PacketContainer(TYPE), TYPE);
    //    handle.getModifier().writeDefaults();
    //}

    public WrapperPlayClientSettings(PacketContainer packet) {
        super(packet, TYPE);
    }
    public static int getViewDistanceOld() {
        return handle.getIntegers().read(0);
    }

    //public static void setViewDistanceOld(byte value) {
    //    handle.getIntegers().write(0, (int) value);
    //}

    // Maybe in a future update...?
}