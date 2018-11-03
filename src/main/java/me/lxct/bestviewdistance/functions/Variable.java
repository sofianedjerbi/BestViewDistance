package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.Main;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Variable{
    public static int max = Main.plugin.getConfig().getInt("ViewDistance.Max");
    public static int min = Main.plugin.getConfig().getInt("ViewDistance.Min");
    // public static int min = Main.plugin.getConfig().getInt("ViewDistance.Delay"); not needed.
    public static int rping = Main.plugin.getConfig().getInt("Performances.PingForReduction");
    public static int aping = Main.plugin.getConfig().getInt("Performances.PingForAugmentation");
    public static double tpslimit = Main.plugin.getConfig().getDouble("Performances.TPSLimit");
    public static double tpschange = Main.plugin.getConfig().getDouble("Performances.TPSChangeIndice");
    public static double maxindice = Main.plugin.getConfig().getDouble("Performances.MaxReductionIndice");
    static HashMap<String, Location> playerLocation = new HashMap<>();
    public static HashMap<String, Integer> playerViewDistance = new HashMap<>();
    public static List<String> afkList = new ArrayList<>();
    public static Double reductionIndice = 0.0;
}
