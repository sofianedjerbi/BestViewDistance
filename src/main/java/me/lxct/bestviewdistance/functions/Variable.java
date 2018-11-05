package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Variable{
    public static int max = BestViewDistance.plugin.getConfig().getInt("ViewDistance.Max");
    public static int min = BestViewDistance.plugin.getConfig().getInt("ViewDistance.Min");
    static int afk = BestViewDistance.plugin.getConfig().getInt("ViewDistance.AFK");
    // public static int min = Main.plugin.getConfig().getInt("ViewDistance.Delay"); not needed.
    public static int rping = BestViewDistance.plugin.getConfig().getInt("Performances.PingForReduction");
    public static int aping = BestViewDistance.plugin.getConfig().getInt("Performances.PingForAugmentation");
    public static double tpslimit = BestViewDistance.plugin.getConfig().getDouble("Performances.TPSLimit");
    public static double tpschange = BestViewDistance.plugin.getConfig().getDouble("Performances.TPSChangeIndice");
    public static double maxindice = BestViewDistance.plugin.getConfig().getDouble("Performances.MaxReductionIndice");
    static HashMap<String, Location> playerLocation = new HashMap<>();
    public static HashMap<String, Integer> playerViewDistance = new HashMap<>();
    public static List<String> afkList = new ArrayList<>();
    public static Double reductionIndice = 0.0;
}
