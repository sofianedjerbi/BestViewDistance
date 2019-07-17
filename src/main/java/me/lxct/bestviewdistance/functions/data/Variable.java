package me.lxct.bestviewdistance.functions.data;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.BVDPlayer;
import me.lxct.bestviewdistance.functions.BVDTimings;
import me.lxct.bestviewdistance.functions.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

import static me.lxct.bestviewdistance.commands.Commands.colorize;

public class Variable {

    //
    // Config.yml stuff
    //

    public static int max;
    public static int min;
    public static int afk;
    public static int onLoginDelay;
    public static int onLoginView;
    public static int onTeleportView;
    public static int onFlyingView;
    public static int rping;
    public static int aping;
    public static int safePing;
    public static int teleportUnsetDelay;
    public static int moreThanSettings;
    public static int decimalsTPS;
    public static int decimalsIndice;
    public static double tpsLimit;
    public static double tpsChange;
    public static double maxIndice;
    public static boolean hideVdistLine4;
    public static boolean useTeleportView;
    public static boolean useOnFlyingView;
    public static boolean useTasks;
    public static boolean usePing;
    public static boolean useTPS;
    public static boolean useAFKView;
    public static boolean useLoginView;
    public static boolean usePermissions;
    public static boolean usePerRegionFeature;
    public static boolean usePerWorldFeature;
    public static boolean permissionsBypassAFK;
    public static boolean permissionsBypassFlying;
    public static boolean permissionsBypassTeleport;


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


    public static BVDPlayer playerData; // Player var used in commands for messages.yml
    public static ConcurrentHashMap<Player, BVDPlayer> onlinePlayers = new ConcurrentHashMap<>(); // All online players BVD players
    public static final String serverVersion = Bukkit.getServer().getVersion(); // Get server version
    public static BVDTimings timings = new BVDTimings(); // Initialize the Reduction indice

    public static void loadVariables() {

        // FILES
        final FileConfiguration configYml = BestViewDistance.plugin.getConfig();
        final Messages messages = new Messages();
        final FileConfiguration messagesYml = messages.get();
        // FILES

        //
        // Config.yml stuff
        //

        max = configYml.getInt("ViewDistance.Max", 16);
        afk = configYml.getInt("ViewDistance.OnAFK", 3);
        min = configYml.getInt("ViewDistance.Min", 4);
        safePing = configYml.getInt("Settings.SafePing", 1);
        onLoginView = configYml.getInt("ViewDistance.OnLogin", 4);
        onFlyingView = configYml.getInt("ViewDistance.OnFlying", 12);
        onTeleportView = configYml.getInt("ViewDistance.OnTeleport", 4);
        teleportUnsetDelay = configYml.getInt("Delay.UnsetTeleportViewDelay", 3);
        onLoginDelay = configYml.getInt("Delay.OnLoginDelay", 10);
        rping = configYml.getInt("Settings.PingForReduction", 550);
        aping = configYml.getInt("Settings.PingForAugmentation", 90);
        moreThanSettings = configYml.getInt("ViewDistance.MoreThanSettings", 0);
        tpsLimit = configYml.getDouble("Settings.TpsLimit", 19.5);
        tpsChange = configYml.getDouble("Settings.TpsChangeIndice", 0.01);
        maxIndice = configYml.getDouble("Settings.MaxReductionIndice", 0.75);
        hideVdistLine4 = configYml.getBoolean("Misc.HideVdistLine4", false);
        useTeleportView = configYml.getBoolean("Features.UseTeleportView", false);
        useAFKView = configYml.getBoolean("Features.UseAFKView", true);
        useOnFlyingView = configYml.getBoolean("Features.UseFlyingView", false);
        useTasks = configYml.getBoolean("Features.UseTasks", true);
        usePing = configYml.getBoolean("Features.UsePing", true);
        useTPS = configYml.getBoolean("Features.UseTPS", true);
        useLoginView = configYml.getBoolean("Features.UseLoginView", true);
        usePermissions = configYml.getBoolean("Features.UsePermissions", false);
        usePerRegionFeature = configYml.getBoolean("Features.UsePerRegionFeature", false);
        usePerWorldFeature = configYml.getBoolean("Features.UsePerWorldFeature", false);
        decimalsTPS = configYml.getInt("Misc.DecimalsTPS", 90);
        decimalsIndice = configYml.getInt("Misc.DecimalsIndice", 90);
        permissionsBypassAFK = configYml.getBoolean("Permissions.BypassAFKView", true);
        permissionsBypassFlying = configYml.getBoolean("Permissions.BypassFlyingView", true);
        permissionsBypassTeleport = configYml.getBoolean("Permissions.BypassTeleportView", true);

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

        if (!configYml.isBoolean("Permissions.BypassAFKView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"BypassAFKView\" value is wrong!"));
        }
        if (!configYml.isBoolean("Permissions.BypassTeleportView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"BypassTeleportView\" value is wrong!"));
        }
        if (!configYml.isBoolean("Permissions.BypassFlyingView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"BypassFlyingView\" value is wrong!"));
        }
        if (!configYml.isInt("Misc.DecimalsTPS")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"DecimalsTPS\" value is wrong!"));
        }
        if (!configYml.isInt("Misc.DecimalsIndice")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"DecimalsIndice\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UsePermissions")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UsePermissions\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UseAFKView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UseAFKView\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UseLoginView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UseLoginView\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UseTPS")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UseTPS\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UsePing")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UsePing\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UseFlyingView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UseFlyingView\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.OnFlying")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"OnFlying\" value is wrong!"));
        }
        if (!configYml.isInt("Delay.CheckFlyingDelay")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"CheckFlyingDelay\" value is wrong!"));
        }
        //
        if (!configYml.isInt("Delay.SetViewDelay")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"SetViewDelay\" value is wrong!"));
        }
        if (!configYml.isInt("Delay.AFKDelay")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"AFKTimer\" value is wrong!"));
        }
        if (!configYml.isInt("Delay.CalculationsDelay")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"CalculationsDelay\" value is wrong!"));
        }
        //
        if (!configYml.isInt("ViewDistance.Max")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Max\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.OnAFK")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"OnAFK\" value is wrong!"));
        }
        if (!configYml.isInt("ViewDistance.Min")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"Min\" value is wrong!"));
        }
        if (!configYml.isInt("Settings.SafePing")) {
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
        if (!configYml.isInt("Delay.UnsetTeleportViewDelay")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UnsetTeleportViewDelay\" value is wrong!"));
        }
        if (!configYml.isInt("Settings.PingForReduction")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"PingForReduction\" value is wrong!"));
        }
        if (!configYml.isInt("Settings.PingForAugmentation")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"PingForAugmentation\" value is wrong!"));
        }
        if (!configYml.isDouble("Settings.TpsLimit")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"tpsLimit\" value is wrong!"));
        }
        if (!configYml.isDouble("Settings.TpsChangeIndice")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"tpsChangeIndice\" value is wrong!"));
        }
        if (!configYml.isDouble("Settings.MaxReductionIndice")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"MaxReductionIndice\" value is wrong!"));
        }
        if (!configYml.isBoolean("Misc.HideVdistLine4")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"HideVdistLine4\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UseTeleportView")) {
            Bukkit.getConsoleSender().sendMessage(colorize("[BestViewDistance] &4&lWARNING! \"UseTeleportView\" value is wrong!"));
        }
        if (!configYml.isBoolean("Features.UseTasks")) {
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