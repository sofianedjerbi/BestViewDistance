package me.lxct.bestviewdistance;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.lxct.bestviewdistance.commands.ViewCommand;
import me.lxct.bestviewdistance.event.OnLogin;
import me.lxct.bestviewdistance.event.OnPlayerMove;
import me.lxct.bestviewdistance.functions.AsyncUpdateChecker;
import me.lxct.bestviewdistance.functions.Other;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static me.lxct.bestviewdistance.functions.Get.getNewReductionIndice;
import static me.lxct.bestviewdistance.functions.Other.genMessagesYml;
import static me.lxct.bestviewdistance.functions.Other.loadMessagesYml;
import static me.lxct.bestviewdistance.functions.Set.setPlayersBestViewDistance;
import static me.lxct.bestviewdistance.functions.Set.setServerLimits;
import static me.lxct.bestviewdistance.functions.data.Variable.loadVariables;

public class BestViewDistance extends JavaPlugin{

    public static BestViewDistance plugin;

    @Override
    public void onEnable(){
        plugin=this; // Allow BestViewDistance.plugin
        // WARNING
        Bukkit.getLogger().info("╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐"); // Display
        Bukkit.getLogger().info("╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤ ");
        Bukkit.getLogger().info("╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘");
        Bukkit.getLogger().info("╚ Make sure you use this plugin with Paper.");
        Bukkit.getLogger().info("╚ https://papermc.io/");
        Bukkit.getLogger().info("╚ Best View Distance, By Lxct. ");
        // WARNING

        //
        // 1.12 compatibility
        //

        if (Bukkit.getVersion().contains("1.12")) { // Add 1.12 Support for Client View Distance
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            protocolManager.addPacketListener(new PacketAdapter(BestViewDistance.plugin,
                    ListenerPriority.NORMAL,
                    PacketType.Play.Client.SETTINGS) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    if (event.getPacketType() == PacketType.Play.Client.SETTINGS) {
                        PacketContainer packet = event.getPacket();
                        Variable.playerSettingsViewDistance.put(event.getPlayer().getName(), packet.getIntegers().read(0));

                    }
                }
            });
        }

        //
        // 1.12 compatibility
        //

        getServer().getPluginManager().registerEvents(new OnLogin(), this); // Add OnLogin Event
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this); // Add OnPlayerMove Event
        saveDefaultConfig(); // GENERATE
        genMessagesYml(); // Generate Messages.yml
        loadMessagesYml(); // Load CustomConfig (Messages)
        loadVariables(); // Load Variables (Config / Messages)
        getCommand("view").setExecutor(new ViewCommand()); // Executor for commands
        getCommand("vdist").setExecutor(new ViewCommand());
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, calculations, 0L,this.getConfig().getInt("ViewDistance.Delay")*20L); // CALCULATIONS SCHEDULER
        //noinspection deprecation
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, detectAFK, 0L,this.getConfig().getInt("Performances.AFKTimer")*20L); // DETECT AFK SCHEDULER
        if(this.getConfig().getBoolean("Other.Metrics")) {
            //noinspection unused
            Metrics metrics = new Metrics(this); // METRICS
        }
        new AsyncUpdateChecker(this).checkForUpdate(); // Add AsyncUpdateChecker (Thx Benz56)
    }

    private Runnable calculations = // CALCULATIONS
            () -> {
                Variable.reductionIndice = getNewReductionIndice(Bukkit.getTPS()[0]); // Update Reduction Indice
                setServerLimits(); // Control
                setPlayersBestViewDistance(Variable.reductionIndice); // Update Players View Distance
            };

    private Runnable detectAFK = // CHECK IF AFK
            Other::putPlayerAFK;
}