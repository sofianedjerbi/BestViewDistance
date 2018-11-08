package me.lxct.bestviewdistance.functions.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.ChatVisibility;

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
     * Retrieve Locale.
     * <p>
     * Notes: en_GB
     *
     * @return The current Locale
     */
    public String getLocale() {
        return handle.getStrings().read(0);
    }

    /**
     * Set Locale.
     *
     * @param value - new value.
     */
    public void setLocale(String value) {
        handle.getStrings().write(0, value);
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

    /**
     * Set View distance.
     *
     * @param value - new value.
     */
    public void setViewDistance(byte value) {
        handle.getIntegers().write(0, (int) value);
    }

    /**
     * Retrieve Chat flags.
     * <p>
     * Notes: chat settings. See notes below.
     *
     * @return The current Chat flags
     */
    public ChatVisibility getChatFlags() {
        return handle.getChatVisibilities().read(0);
    }

    /**
     * Set Chat flags.
     *
     * @param value - new value.
     */
    public void setChatFlags(ChatVisibility value) {
        handle.getChatVisibilities().write(0, value);
    }

    /**
     * Retrieve Chat colours.
     * <p>
     * Notes: "Colours" multiplayer setting
     *
     * @return The current Chat colours
     */
    public boolean getChatColours() {
        return handle.getBooleans().read(0);
    }

    /**
     * Set Chat colours.
     *
     * @param value - new value.
     */
    public void setChatColours(boolean value) {
        handle.getBooleans().write(0, value);
    }

    /**
     * Retrieve Displayed skin parts.
     * <p>
     * Notes: skin parts. See note below
     *
     * @return The current Displayed skin parts
     */
    public int getDisplayedSkinParts() {
        return handle.getIntegers().read(1);
    }

    /**
     * Set Displayed skin parts.
     *
     * @param value - new value.
     */
    public void setDisplayedSkinParts(int value) {
        handle.getIntegers().write(1, value);
    }

}
