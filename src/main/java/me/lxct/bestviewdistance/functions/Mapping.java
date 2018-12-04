/*
package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.functions.data.Map;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.lxct.bestviewdistance.functions.Get.getViewDistance;

public class Mapping {
    public static List<Map> generateMapX(Player player) {
        List<Map> mapX = new ArrayList<>();
        mapX.add(new Map(null, null, null, null));
        int distance = 16 * getViewDistance(player); // Blocks the player can see
        int playerX = player.getLocation().getBlockX();
        int playerZ = player.getLocation().getBlockZ();
        for (int y = 0; y <= 256; y++) { // Start at 0, to 256 (Y LOOP)
            for (int z = playerZ - distance; z <= playerZ + distance; z++) { // Z LOOP
                for (int x = playerX - distance; x <= playerX + distance; x++) { // X LOOP
                    Block block = player.getWorld().getBlockAt(x, y, z);
                    if (block.isEmpty() || block.isLiquid() || block.isPassable()) { // IF THE PLAYER CAN SEE THOUGHT THE BLOCK
                        mapX.add(new Map(x, y, z, true));
                    } else {
                        mapX.add(new Map(x, y, z, false));
                    }
                }
            }
        }
        return mapX;
    }
    public static void generadPlayerMap(Player player) {

    }
}
*/
