package me.lxct.bestviewdistance.functions.data;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.Other;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static me.lxct.bestviewdistance.commands.Commands.colorize;

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
    public static int moreThanSettings;
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
    public static ConcurrentHashMap<String, Location> playerLocation = new ConcurrentHashMap<>(); // Location list
    public static ConcurrentHashMap<String, Integer> playerViewDistance = new ConcurrentHashMap<>(); // View Distance list
    public static ConcurrentHashMap<String, Integer> playerLiveViewDistance = new ConcurrentHashMap<>(); // Live View Distance list
    public static ConcurrentHashMap<String, Integer> playerSettingsViewDistance = new ConcurrentHashMap<>(); // 1.12 Settings View Distance list
    public static ConcurrentHashMap<String, Integer> waitForTPUnset = new ConcurrentHashMap<>(); // Waiting for teleport unset list with task ID
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

        max = configYml.getInt("ViewDistance.Max", 16);
        afk = configYml.getInt("ViewDistance.AFK", 3);
        min = configYml.getInt("ViewDistance.Min", 4);
        safePing = configYml.getInt("Other.SafePing", 1);
        onloginview = configYml.getInt("ViewDistance.OnLogin", 4);
        onteleportview = configYml.getInt("ViewDistance.OnTeleport", 4);
        teleportunset = configYml.getInt("ViewDistance.UnsetTeleportViewDelay", 3);
        rping = configYml.getInt("Performances.PingForReduction", 550);
        aping = configYml.getInt("Performances.PingForAugmentation", 90);
        moreThanSettings = configYml.getInt("ViewDistance.MoreThanSettings", 0);
        tpslimit = configYml.getDouble("Performances.TPSLimit", 19.5);
        tpschange = configYml.getDouble("Performances.TPSChangeIndice", 0.01);
        maxindice = configYml.getDouble("Performances.MaxReductionIndice", 0.75);
        hideVdistLine4 = configYml.getBoolean("Other.HideVdistLine4", false);
        reduceOnTeleport = configYml.getBoolean("Other.ReduceViewOnTeleport", false);
        useTasks = configYml.getBoolean("Performances.UseTasks", true);

        //
        // Messages.yml stuff
        //

        //help
        viewHelpLine1 = messagesYml.getString("help.line1", "&d&l======= BestViewDistance By Lxct =======");
        viewHelpLine2 = messagesYml.getString("help.line2", "&d/vdist =>&a View your own view distance.");
        viewHelpLine3 = messagesYml.getString("help.line3", "&d/view server =>&a Get reduction indice.");
        viewHelpLine4 = messagesYml.getString("help.line4", "&d/view tps =>&a Get server's tps.");
        viewHelpLine5 = messagesYml.getString("help.line5", "&d/view ping <player> =>&a Get player ping.");
        viewHelpLine6 = messagesYml.getString("help.line6", "&d/view <player> =>&a Get player view distance info.");
        viewHelpLine7 = messagesYml.getString("help.line7", "&d/view =>&a This message.");

        //viewPlayer
        viewPlayerLine1 = messagesYml.getString("viewPlayer.line1", "&aView Distance of %PLAYER% =>&d %VIEWDISTANCE% (Current)");
        viewPlayerLine2 = messagesYml.getString("viewPlayer.line2", "&aView Distance of %PLAYER% =>&d %PINGVIEW% (Supported)");
        viewPlayerLine3 = messagesYml.getString("viewPlayer.line3", "&aView Distance of %PLAYER% =>&d %SETTINGS% (Settings)");

        //vdist
        vdistLine1 = messagesYml.getString("vdist.line1", "&aView Distance =>&d %VIEWDISTANCE% (Current)");
        vdistLine2 = messagesYml.getString("vdist.line2", "&aView Distance =>&d %PINGVIEW% (Supported)");
        vdistLine3 = messagesYml.getString("vdist.line3", "&aView Distance =>&d %SETTINGS% (Settings)");
        vdistLine4 = messagesYml.getString("vdist.line4", "&aYour View Distance is reduced by&d %REDUCTIONINDICE%%");
        vping = messagesYml.getString("vdist.vping", "&aYour ping :&d %PING%ms");

        //other
        viewUpdate = messagesYml.getString("view.update", "&a[BestViewDistance]&7 A new update is available at&a");
        viewUpdateFail = messagesYml.getString("view.updateFail", "&a[BestViewDistance]&c Update checker failed!");
        viewReload = messagesYml.getString("view.reload", "&aBest View Distance config reloaded!");
        viewPing = messagesYml.getString("view.ping", "&aPing of %PLAYER%:&d %PING%ms");
        viewTps = messagesYml.getString("view.tps", "&aTPS =>&d %TPS%");
        viewServer = messagesYml.getString("view.server", "&aThe view distance is reduced by&d %REDUCTIONINDICE%%");
        viewIncorrectPing = messagesYml.getString("view.incorrectPing", "&c/view ping <player>");
        viewIncorrectView = messagesYml.getString("view.incorrectView", "&c/view <player>");

        //
        // CHECK FOR ERRORS
        //

        //
        // Config.yml stuff
        //

        if (!configYml.isInt("ViewDistance.Max")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Max\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.AFK")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"AFK\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.Min")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Min\" value is wrong!"));
        }
        if (!configYml.isInt("Other.SafePing")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"SafePing\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.OnLogin")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"OnLogin\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.OnTeleport")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"OnTeleport\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.MoreThanSettings")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"MoreThanSettings\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.UnsetTeleportViewDelay")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UnsetTeleportViewDelay\" value is wrong!"));
        }
        if (!configYml.isInt("Performances.PingForReduction")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"PingForReduction\" value is wrong!"));
        }
        if (!configYml.isInt("Performances.PingForAugmentation")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"PingForAugmentation\" value is wrong!"));
        }
        if (!configYml.isDouble("Performances.TPSLimit")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"TPSLimit\" value is wrong!"));
        }
        if (!configYml.isDouble("Performances.TPSChangeIndice")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"TPSChangeIndice\" value is wrong!"));
        }
        if (!configYml.isDouble("Performances.MaxReductionIndice")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"MaxReductionIndice\" value is wrong!"));
        }
        if (!configYml.isBoolean("Other.HideVdistLine4")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"HideVdistLine4\" value is wrong!"));
        }
        if (!configYml.isBoolean("Other.ReduceViewOnTeleport")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"ReduceViewOnTeleport\" value is wrong!"));
        }
        if (!configYml.isBoolean("Performances.UseTasks")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UseTasks\" value is wrong!"));
        }

        //
        // Messages.yml stuff
        //

        //help
        if (!messagesYml.isString("help.line1")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Help Line1\" value is wrong!"));
        }
        if (!messagesYml.isString("help.line2")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Help Line2\" value is wrong!"));
        }
        if (!messagesYml.isString("help.line3")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Help Line3\" value is wrong!"));
        }
        if (!messagesYml.isString("help.line4")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Help Line4\" value is wrong!"));
        }
        if (!messagesYml.isString("help.line5")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Help Line5\" value is wrong!"));
        }
        if (!messagesYml.isString("help.line6")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Help Line6\" value is wrong!"));
        }
        if (!messagesYml.isString("help.line7")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Help Line7\" value is wrong!"));
        }

        //viewPlayer
        if (!messagesYml.isString("viewPlayer.line1")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"ViewPlayer Line1\" value is wrong!"));
        }
        if (!messagesYml.isString("viewPlayer.line2")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"ViewPlayer Line2\" value is wrong!"));
        }
        if (!messagesYml.isString("viewPlayer.line3")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"ViewPlayer Line3\" value is wrong!"));
        }

        //vdist
        if (!messagesYml.isString("vdist.line1")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Vdist Line1\" value is wrong!"));
        }
        if (!messagesYml.isString("vdist.line2")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Vdist Line2\" value is wrong!"));
        }
        if (!messagesYml.isString("vdist.line3")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Vdist Line3\" value is wrong!"));
        }
        if (!messagesYml.isString("vdist.line4")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Vdist Line4\" value is wrong!"));
        }
        if (!messagesYml.isString("vdist.vping")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Vdist Vping\" value is wrong!"));
        }

        //other
        if (!messagesYml.isString("view.update")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Update\" value is wrong!"));
        }
        if (!messagesYml.isString("view.updateFail")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UpdateFail\" value is wrong!"));
        }
        if (!messagesYml.isString("view.reload")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Reload\" value is wrong!"));
        }
        if (!messagesYml.isString("view.ping")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Ping\" value is wrong!"));
        }
        if (!messagesYml.isString("view.tps")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"TPS\" value is wrong!"));
        }
        if (!messagesYml.isString("view.server")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Server\" value is wrong!"));
        }
        if (!messagesYml.isString("view.incorrectPing")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"IncorrectPing\" value is wrong!"));
        }
        if (!messagesYml.isString("view.incorrectView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"IncorrectView\" value is wrong!"));
        }

    }
}