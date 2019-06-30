package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.text.DecimalFormat;

import static me.lxct.bestviewdistance.functions.data.Variable.maxIndice;
import static me.lxct.bestviewdistance.functions.data.Variable.serverVersion;
import static me.lxct.bestviewdistance.functions.data.Variable.useTPS;

public class BVDTimings {

    private Object serverInstance;
    private Field tpsField;

    private double reductionIndice;

    public BVDTimings() {
        this.reductionIndice = 0;
    }

    public double get1minTPS() {
        return Bukkit.getServer().getTPS()[0];
    }

    // CALCULATE NEW REDUCTION INDICE
    public void actualize() {
        if (useTPS) {
            final double TPS = get1minTPS();
            if (TPS > Variable.tpsLimit && TPS < 20) { // If tps > tps limit
                this.reductionIndice = this.reductionIndice - Variable.tpsChange; // Decrease indice
            } else if (TPS < Variable.tpsLimit) { // If tps < tps limit
                this.reductionIndice = this.reductionIndice + Variable.tpsChange; // Increase indice
            }
            this.reductionIndice = Math.max(0.0, Math.min(this.reductionIndice, maxIndice));
        }
    }

    public double getReductionIndice() {
        return this.reductionIndice;
    }
}
