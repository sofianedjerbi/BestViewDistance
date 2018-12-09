package me.lxct.bestviewdistance.functions.data;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.Other;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Variable {

    //
    // Config.yml stuff
    //

    public static int max;
    public static int min;
    public static int afk;
    public static int onloginview;
    public static int onteleportview;
    public static int rping;
    public static int aping;
    public static int safePing;
    public static int teleportunset;
    public static double tpslimit;
    public static double tpschange;
    public static double maxindice;
    public static boolean hideVdistLine4;
    public static boolean reduceOnTeleport;
    public static boolean useTasks;

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
    public static String vping;

    //other
    public static String viewUpdate;
    public static String viewUpdateFail;
    public static String viewReload;
    public static String viewPing;
    public static String viewTps;
    public static String viewServer;
    public static String viewIncorrectPing;
    public static String viewIncorrectView;

    //
    // TMP stuff
    //

    // HashMap<String, ArrayList<Map>> playerBlockMap = new HashMap<>();
    public static HashMap<String, Location> playerLocation = new HashMap<>(); // Location list
    public static HashMap<String, Integer> playerViewDistance = new HashMap<>(); // View Distance list
    public static HashMap<String, Integer> playerLiveViewDistance = new HashMap<>(); // Live View Distance list
    public static HashMap<String, Integer> playerSettingsViewDistance = new HashMap<>(); // 1.12 Settings View Distance list
    public static HashMap<String, Integer> waitForTPUnset = new HashMap<>(); // Waiting for teleport unset list with task ID
    public static List<String> afkList = Collections.synchronizedList(new ArrayList<>()); // AFK list
    public static Player playerData; // Player var used in commands for messages.yml
    public static String playerName; // PlayerNAME var used in commands for messages.yml
    public static Double reductionIndice = 0.0; // Initialize the Reduction indice

    public static void loadVariables() {

        // FILES
        FileConfiguration configYml = BestViewDistance.plugin.getConfig();
        FileConfiguration messagesYml = Other.getCustomConfig();
        // FILES

        //
        // Config.yml stuff
        //

        max = configYml.getInt("ViewDistance.Max");
        afk = configYml.getInt("ViewDistance.AFK");
        min = configYml.getInt("ViewDistance.Min");
        safePing = configYml.getInt("Other.SafePing");
        onloginview = configYml.getInt("ViewDistance.OnLogin");
        onteleportview= configYml.getInt("ViewDistance.OnTeleport");
        teleportunset= configYml.getInt("ViewDistance.UnsetTeleportViewDelay");
        rping = configYml.getInt("Performances.PingForReduction");
        aping = configYml.getInt("Performances.PingForAugmentation");
        tpslimit = configYml.getDouble("Performances.TPSLimit");
        tpschange = configYml.getDouble("Performances.TPSChangeIndice");
        maxindice = configYml.getDouble("Performances.MaxReductionIndice");
        hideVdistLine4 = configYml.getBoolean("Other.HideVdistLine4");
        reduceOnTeleport = configYml.getBoolean("Other.ReduceViewOnTeleport");
        useTasks = configYml.getBoolean("Performances.UseTasks");

        //
        // Messages.yml stuff
        //

        //help
        viewHelpLine1 = messagesYml.getString("help.line1");
        viewHelpLine2 = messagesYml.getString("help.line2");
        viewHelpLine3 = messagesYml.getString("help.line3");
        viewHelpLine4 = messagesYml.getString("help.line4");
        viewHelpLine5 = messagesYml.getString("help.line5");
        viewHelpLine6 = messagesYml.getString("help.line6");
        viewHelpLine7 = messagesYml.getString("help.line7");

        //viewPlayer
        viewPlayerLine1 = messagesYml.getString("viewPlayer.line1");
        viewPlayerLine2 = messagesYml.getString("viewPlayer.line2");
        viewPlayerLine3 = messagesYml.getString("viewPlayer.line3");

        //vdist
        vdistLine1 = messagesYml.getString("vdist.line1");
        vdistLine2 = messagesYml.getString("vdist.line2");
        vdistLine3 = messagesYml.getString("vdist.line3");
        vdistLine4 = messagesYml.getString("vdist.line4");
        vping = messagesYml.getString("vdist.vping");

        //other
        viewUpdate = messagesYml.getString("view.update");
        viewUpdateFail = messagesYml.getString("view.updateFail");
        viewReload = messagesYml.getString("view.reload");
        viewPing = messagesYml.getString("view.ping");
        viewTps = messagesYml.getString("view.tps");
        viewServer = messagesYml.getString("view.server");
        viewIncorrectPing = messagesYml.getString("view.incorrectPing");
        viewIncorrectView = messagesYml.getString("view.incorrectView");
    }
}