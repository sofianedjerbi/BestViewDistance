package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static me.lxct.bestviewdistance.functions.Get.getPlayerPermissions;
import static me.lxct.bestviewdistance.functions.Get.getSettingsViewDistance;
import static me.lxct.bestviewdistance.functions.Get.getViewDistance;
import static me.lxct.bestviewdistance.functions.Limit.limitClientSetting;
import static me.lxct.bestviewdistance.functions.Limit.limitSupportedView;
import static me.lxct.bestviewdistance.functions.Set.setPlayerPermissions;
import static me.lxct.bestviewdistance.functions.Set.setViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Other {

    public static void AFKChecker() { // What this function does ? if the player has exactly the same position as x minutes ago, he'll be set in "AFK" mode.
        for (Player player : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            if (!getPlayerPermissions(player)) {
                Location location = player.getLocation(); // Get Location
                if (location.equals(playerLocation.get(player.getName()))) { // If same position ...
                    if (!afkList.contains(player.getName())) { // If player is not afk
                        afkList.add(player.getName()); // SET AFK
                    }
                } else { // If it's not the same position...
                    playerLocation.put(player.getName(), player.getLocation()); // Actualize the position.
                }
            } else {
                if(!permissionsBypassAFK){
                    Location location = player.getLocation(); // Get Location
                    if (location.equals(playerLocation.get(player.getName()))) { // If same position ...
                        if (!afkList.contains(player.getName())) { // If player is not afk
                            afkList.add(player.getName()); // SET AFK
                        }
                    } else { // If it's not the same position...
                        playerLocation.put(player.getName(), player.getLocation()); // Actualize the position.
                    }
                }
            }
        }
    }

    public static void flyingChecker() { // What this function does ? if the player is still flying, we'll set this view distance to "flying" value
        for (Player player : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            if (!getPlayerPermissions(player)) {
                if (player.isFlying()) { // If flying
                    if (!flyingList.contains(player.getName())) { // IF flying && not in the list
                        flyingList.add(player.getName());
                    }
                } else { // If not flying
                    flyingList.remove(player.getName());
                }
            } else {
                if(!permissionsBypassFlying) {
                    if (player.isFlying()) { // If flying
                        if (!flyingList.contains(player.getName())) { // IF flying && not in the list
                            flyingList.add(player.getName());
                        }
                    } else { // If not flying
                        flyingList.remove(player.getName());
                    }
                }
            }
        }
    }

    // A FUNCTION THAT SET THE VIEW DISTANCE WITH FUNCTION THAT BREAK ASYNC CHAINS
    public static void applyViewDistance() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String pName = player.getName();
            if (afkList.contains(pName) && player.getViewDistance() != afk && useAFKView) { // IF player is afk
                setViewDistance(player, afk);
            } else if (flyingList.contains(pName)
                    && player.getViewDistance() != onFlyingView
                    && useOnFlyingView) { // FLYING VIEW
                setViewDistance(player, onFlyingView);
            } else {
                if (playerLiveViewDistance.get(pName) != null  && waitForTPUnset.get(pName) == null) {
                    int vdistToApply = playerLiveViewDistance.get(pName) + moreThanSettings;
                    if (setPlayerPermissions(player, limitClientSetting(player, limitSupportedView(player, vdistToApply))) != getViewDistance(player)) {
                        setViewDistance(player, vdistToApply);
                    }
                }
            }
        }
    }

    static void handler(Exception e) {
        e.printStackTrace();
        Bukkit.getServer().getLogger().severe("Serious NMS error.");
        Bukkit.getPluginManager().disablePlugin(BestViewDistance.plugin);
        e.printStackTrace();
    }

    public static void genOnlinePlayerData() { // Set all playerLiveViewDistance to onLoginView.
        playerLiveViewDistance.clear();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            playerLiveViewDistance.put(player.getName(), Variable.onLoginView);
        }
    }

    public static void loadMessagesYml() { // Load messages.yml
        FileConfiguration customConfig;
        File customConfigFile = new File(BestViewDistance.plugin.getDataFolder(), "messages.yml");
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void genMessagesYml() { // Generate messages.yml file
        if (!new File(BestViewDistance.plugin.getDataFolder(), "messages.yml").exists()) {
            Bukkit.getLogger().info("[BestViewDistance] Generating messages.yml...");
            BestViewDistance.plugin.saveResource("messages.yml", false);
            Bukkit.getLogger().info("[BestViewDistance] messages.yml generated !");
        }
    }

    public static String replacePlaceHolders(String string) { // Replace placeholders in messages.yml
        if (string.contains("%TPS%")) {
            string = string.replace("%TPS%", String.valueOf(Get.get1minTPS()));
        }
        if (string.contains("%PLAYER%")) {
            string = string.replace("%PLAYER%", playerName);
        }
        if (string.contains("%VIEWDISTANCE%")) {
            string = string.replace("%VIEWDISTANCE%", String.valueOf(getViewDistance(playerData)));
        }
        if (string.contains("%SETTINGS%")) {
            string = string.replace("%SETTINGS%", String.valueOf(getSettingsViewDistance(playerData)));
        }
        if (string.contains("%REDUCTIONINDICE%")) {
            string = string.replace("%REDUCTIONINDICE%", String.valueOf(Math.round(reductionIndice * 100)));
        }
        if (string.contains("%PING%")) {
            string = string.replace("%PING%", String.valueOf(playerData.spigot().getPing()));
        }
        if (string.contains("%PINGVIEW%")) {
            string = string.replace("%PINGVIEW%", String.valueOf(playerViewDistance.get(playerData.getName())));
        }
        return string;
    }
}