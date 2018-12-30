package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

import static me.lxct.bestviewdistance.functions.data.Variable.maxIndice;

public class BVDTimings {

    private Object serverInstance;
    private Field tpsField;

    private double reductionIndice;

    public BVDTimings() {
        this.reductionIndice = 0;
        if (Bukkit.getVersion().contains("1.8")) {
            try {
                String name = Bukkit.getServer().getClass().getPackage().getName();
                String version = name.substring(name.lastIndexOf('.') + 1);
                this.serverInstance = Class.forName("net.minecraft.server." + version + "." + "MinecraftServer").getMethod("getServer").invoke(null);
                this.tpsField = serverInstance.getClass().getField("recentTps");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private String getTPS() { // Get TPS (USED ONLY FOR 1.8)
        try {
            double[] tps = ((double[]) this.tpsField.get(this.serverInstance));
            return new DecimalFormat("##.##").format(tps[0]).replace(",", ".");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public double get1minTPS() {
        if (Bukkit.getVersion().contains("1.8")) {
            return Double.valueOf(getTPS());
        } else {
            return Bukkit.getServer().getTPS()[0];
        }
    }

    // CALCULATE NEW REDUCTION INDICE
    public void actualize() {
        double TPS = get1minTPS();
        double tmp = this.reductionIndice;
        if (TPS > Variable.tpsLimit && TPS < 20) { // If tps > tps limit
            tmp = tmp - Variable.tpsChange; // Decrease indice
        } else if (TPS < Variable.tpsLimit) { // If tps < tps limit
            tmp = tmp + Variable.tpsChange; // Increase indice
        }
        this.reductionIndice = Math.min(0.0, Math.min(tmp, maxIndice));
    }
    public double getReductionIndice() {
        return this.reductionIndice;
    }
}
