package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

public class Get {

    private static final String name = Bukkit.getServer().getClass().getPackage().getName();
    private static final String version = name.substring(name.lastIndexOf('.') + 1);
    private static final DecimalFormat format = new DecimalFormat("##.##");
    public static Object serverInstance;
    public static Field tpsField;

    public static Class<?> getNMSClass(String className) { // Get NMS class
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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


    static int getViewDistance(Player player) { // Get View Distance in settings
        if (Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.11") || Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.9") || Bukkit.getVersion().contains("1.8")) {
            if (Variable.playerSettingsViewDistance.containsKey(player.getName())) {
                return Variable.playerSettingsViewDistance.get(player.getName());
            } else {
                return Variable.min;
            }
        } else {
            //noinspection deprecation
            return player.getClientViewDistance();
        }
    }

    private static String getTPS() { // Get TPS (USED ONLY FOR 1.8)
        try {
            double[] tps = ((double[]) tpsField.get(serverInstance));
            return format.format(tps[0]).replace(",", ".");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static double get1minTPS() {
        if (Bukkit.getVersion().contains("1.8")) {
            return Double.valueOf(getTPS());
        } else {
            return Bukkit.getServer().getTPS()[0];
        }
    }
}