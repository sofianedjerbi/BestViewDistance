package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.main;

class variables {
    static int max = main.plugin.getConfig().getInt("ViewDistance.Max");
    static int min = main.plugin.getConfig().getInt("ViewDistance.Min");
    //public static int min = main.plugin.getConfig().getInt("ViewDistance.Delay"); not needed.
    static int rping = main.plugin.getConfig().getInt("Performances.PingForReduction");
    static int aping = main.plugin.getConfig().getInt("Performances.PingForAugmentation");
    static int tpslimit = main.plugin.getConfig().getInt("Performances.TPSLimit");
    static int tpschange = main.plugin.getConfig().getInt("Performances.TPSChangeIndice");
    static int maxindice = main.plugin.getConfig().getInt("Performances.MaxReductionIndice");
}
