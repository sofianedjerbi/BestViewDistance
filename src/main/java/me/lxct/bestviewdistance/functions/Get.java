package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;

import static me.lxct.bestviewdistance.functions.Other.handler;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Get {

    private static final String name = Bukkit.getServer().getClass().getPackage().getName();
    private static final String version = name.substring(name.lastIndexOf('.') + 1);
    private static final DecimalFormat format = new DecimalFormat("##.##");
    public static Object serverInstance;
    public static Field tpsField;

    static int getMaxWorldLimits(World world) {
        String worldName = world.getName();
        if (BestViewDistance.plugin.getConfig().isInt("Worlds." + worldName + ".Max")) {
            return BestViewDistance.plugin.getConfig().getInt("Worlds." + worldName + ".Max");
        }
        return max;
    }

    static int getMinWorldLimits(World world) {
        String worldName = world.getName();
        if (BestViewDistance.plugin.getConfig().isInt("Worlds." + worldName + ".Min")) {
            return BestViewDistance.plugin.getConfig().getInt("Worlds." + worldName + ".Min");
        }
        return min;
    }

    public static Class<?> getNMSClass(String className) { // Get NMS class
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // CALCULATE NEW REDUCTION INDICE
    public static double getNewReductionIndice(Double TPS) {
        if (TPS > Variable.tpsLimit && TPS < 20) { // If tps > tps limit
            return Variable.reductionIndice - Variable.tpsChange; // Decrease indice
        } else if (TPS < Variable.tpsLimit) { // If tps < tps limit
            return Variable.reductionIndice + Variable.tpsChange; // Increase indice
        } else {
            return Variable.reductionIndice; // Nothing.
        }
    }

    static Object getPrivateField(String name, Class<?> clazz, Object o) {

        Field f;
        Object obj = null;

        try {
            f = clazz.getDeclaredField(name);
            f.setAccessible(true);
            obj = f.get(o);
        } catch (Exception e) {
            handler(e);
        }

        return obj;
    }

    // CHECK AND USE PERMISSIONS
    public static boolean getPlayerPermissions(Player player) {
        if(usePermissions) {
            for (int i = 32; i >= 3; i--) { // Start at 32, to 3
                // 3 4 5 6 7 8 9 10 ... 30 31 32
                if (player.hasPermission("view.set." + i)) { // view.set.i is set
                    return true; // If he has permission, then return true
                }
            }
        }
        return false; // If he doesn't have permissions, then return false
    }


    public static FileConfiguration getCustomConfig() {
        FileConfiguration customConfig;
        File customConfigFile = new File(BestViewDistance.plugin.getDataFolder(), "messages.yml");
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        return customConfig;
    }


    public static int getSettingsViewDistance(Player player) { // Get View Distance in settings
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

    static int getViewDistance(Player player) {
        if (!Bukkit.getVersion().contains("1.8")) {
            return player.getViewDistance();
        } else {
            return player.getServer().getViewDistance();
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