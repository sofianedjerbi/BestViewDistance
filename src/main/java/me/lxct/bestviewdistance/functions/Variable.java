package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Variable {

    //
    // Config.yml stuff
    //

    static int max;
    public static int min;
    static int afk;
    static int rping;
    static int aping;
    static double tpslimit;
    static double tpschange;
    static double maxindice;
    public static boolean hidereductionindice;

    //
    // Messages.yml stuff
    //

    //help
    public static String viewHelpLine1;
    public static String viewHelpLine2;
    public static String viewHelpLine3;
    public static String viewHelpLine4;
    public static String viewHelpLine5;
    public static String viewHelpLine6;
    public static String viewHelpLine7;

    //viewPlayer
    public static String viewPlayerLine1;
    public static String viewPlayerLine2;
    public static String viewPlayerLine3;

    //vdist
    public static String vdistLine1;
    public static String vdistLine2;
    public static String vdistLine3;
    public static String vdistLine4;

    //other
    public static String viewReload;
    public static String viewPing;
    public static String viewTps;
    public static String viewServer;
    public static String viewOffline;
    public static String viewIncorrectPing;
    public static String viewIncorrectView;

    //
    // TMP stuff
    //

    static HashMap<String, Location> playerLocation = new HashMap<>(); // Location list
    public static HashMap<String, Integer> playerViewDistance = new HashMap<>(); // View Distance list
    public static List<String> afkList = new ArrayList<>(); // AFK list
    public static Player player; // Player var used in commands for messages.yml
    public static Double reductionIndice = 0.0; // Initialize the Reduction indice
    static int tmpClientViewDistance;

    public static void loadVariables() {

        //
        // Config.yml stuff
        //

        max = BestViewDistance.plugin.getConfig().getInt("ViewDistance.Max");
        afk = BestViewDistance.plugin.getConfig().getInt("ViewDistance.AFK");
        min = BestViewDistance.plugin.getConfig().getInt("ViewDistance.Min");
        rping = BestViewDistance.plugin.getConfig().getInt("Performances.PingForReduction");
        aping = BestViewDistance.plugin.getConfig().getInt("Performances.PingForAugmentation");
        tpslimit = BestViewDistance.plugin.getConfig().getDouble("Performances.TPSLimit");
        tpschange = BestViewDistance.plugin.getConfig().getDouble("Performances.TPSChangeIndice");
        maxindice = BestViewDistance.plugin.getConfig().getDouble("Performances.MaxReductionIndice");
        hidereductionindice = BestViewDistance.plugin.getConfig().getBoolean("Other.HideReductionIndiceInVdist");

        //
        // Messages.yml stuff
        //

        //help
        viewHelpLine1 = Other.getCustomConfig().getString("help.line1");
        viewHelpLine2 = Other.getCustomConfig().getString("help.line2");
        viewHelpLine3 = Other.getCustomConfig().getString("help.line3");
        viewHelpLine4 = Other.getCustomConfig().getString("help.line4");
        viewHelpLine5 = Other.getCustomConfig().getString("help.line5");
        viewHelpLine6 = Other.getCustomConfig().getString("help.line6");
        viewHelpLine7 = Other.getCustomConfig().getString("help.line7");

        //viewPlayer
        viewPlayerLine1 = Other.getCustomConfig().getString("viewPLayer.line1");
        viewPlayerLine2 = Other.getCustomConfig().getString("viewPLayer.line2");
        viewPlayerLine3 = Other.getCustomConfig().getString("viewPLayer.line3");

        //vdist
        vdistLine1 = Other.getCustomConfig().getString("vdist.line1");
        vdistLine2 = Other.getCustomConfig().getString("vdist.line2");
        vdistLine3 = Other.getCustomConfig().getString("vdist.line3");
        vdistLine4 = Other.getCustomConfig().getString("vdist.line4");


        //other
        viewReload = Other.getCustomConfig().getString("view.reload");
        viewPing = Other.getCustomConfig().getString("view.ping");
        viewTps = Other.getCustomConfig().getString("view.tps");
        viewServer = Other.getCustomConfig().getString("view.server");
        viewOffline = Other.getCustomConfig().getString("view.offline");
        viewIncorrectPing = Other.getCustomConfig().getString("view.incorrectPing");
        viewIncorrectView = Other.getCustomConfig().getString("view.incorrectView");
    }
}