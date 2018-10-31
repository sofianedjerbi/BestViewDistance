package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.main;

public class variable{
    public static int max = main.plugin.getConfig().getInt("ViewDistance.Max");
    public static int min = main.plugin.getConfig().getInt("ViewDistance.Min");
    //public static int min = main.plugin.getConfig().getInt("ViewDistance.Delay"); not needed.
    public static int rping = main.plugin.getConfig().getInt("Performances.PingForReduction");
    public static int aping = main.plugin.getConfig().getInt("Performances.PingForAugmentation");
    public static double tpslimit = main.plugin.getConfig().getDouble("Performances.TPSLimit");
    public static double tpschange = main.plugin.getConfig().getDouble("Performances.TPSChangeIndice");
    public static double maxindice = main.plugin.getConfig().getDouble("Performances.MaxReductionIndice");
}
