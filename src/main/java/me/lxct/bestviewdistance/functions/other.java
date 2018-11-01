package me.lxct.bestviewdistance.functions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;

import static me.lxct.bestviewdistance.functions.set.setPlayerViewDistance;
import static me.lxct.bestviewdistance.functions.variable.*;

public class other {
    public static void putPlayerAFK() { // What this function does ? if the player has exactly the same position as two minutes ago, he'll be set in "AFK" mode.
        for (Player player : Bukkit.getServer().getOnlinePlayers()) { // Every players...
            Location location = player.getLocation(); // Get Location
            if (location.equals(playerLocation.get(player.getName()))){ // If same position ...
                if(!afkList.contains(player.getName())) { // If player is not afk
                    afkList.add(player.getName()); // SET AFK
                }
            } else { // If it's not the same position...
                playerLocation.put(player.getName(), player.getLocation()); // Actualize the position.
            }
        }
    }

    public static void savePlayerViewDistance() {
        for(Map.Entry<String, Integer> entry : playerViewDistance.entrySet()) {
            String name = entry.getKey();
            Integer viewDist = entry.getValue();
            setPlayerViewDistance(Bukkit.getPlayer(name), viewDist);
        }
    }
}