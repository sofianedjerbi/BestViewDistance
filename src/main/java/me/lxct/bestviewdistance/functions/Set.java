package me.lxct.bestviewdistance.functions;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.sync.SetViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Limit.limitClientSetting;
import static me.lxct.bestviewdistance.functions.Limit.limitSupportedView;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class Set {

    // CHECK AND USE PERMISSIONS
    public static int setPlayerPermissions(Player player, int viewDistance) {
        if(usePermissions) {
            for (int i = 32; i >= 3; i--) { // Start at 32, to 3
                // 3 4 5 6 7 8 9 10 ... 30 31 32
                if (player.hasPermission("view.set." + i)) { // view.set.i is set
                    return i; // If he has permission, then return the number "after" the permission.
                }
            }
        }
        return viewDistance; // If he doesn't have permissions, then return viewDistance.
    }

    static void setViewDistance(Player player, int viewDistance) {
        int theViewDistance = setPlayerPermissions(player, limitSupportedView(player, limitClientSetting(player, viewDistance) + moreThanSettings));
        int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, new SetViewDistance(player, theViewDistance)); // Break Async chain
        if (task == -1 && useTasks) {
            Bukkit.getScheduler().runTask(BestViewDistance.plugin, new SetViewDistance(player, theViewDistance)); // Break Async chain
        }
    }

    static void setNoMoreThanSettingsViewDistance(Player player, int viewDistance) {
        int theViewDistance = setPlayerPermissions(player, limitSupportedView(player, limitClientSetting(player, viewDistance)));
        int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, new SetViewDistance(player, theViewDistance)); // Break Async chain
        if (task == -1 && useTasks) {
            Bukkit.getScheduler().runTask(BestViewDistance.plugin, new SetViewDistance(player, theViewDistance)); // Break Async chain
        }
    }
/*
    public static void setRenderDistance(int vdist) {

        //get CraftBukkit CraftWorld class
        Class<?> craftworldclass;
        try {
            craftworldclass = Class.forName("org.bukkit.craftbukkit." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + "CraftWorld");
        } catch (ClassNotFoundException e) {
            handler(e);
            return;
        }

        //get the NMS PlayerChunkManager object for this world
        Object cw = craftworldclass.cast(Bukkit.getWorld("world"));
        Object ws = getPrivateField("world", craftworldclass, cw);
        Object pcm = getPrivateField("manager", ws.getClass(), ws);

        //invoke the public a() method in the PlayerChunkManager class that changes the render distance
        try {
            Method viewDistChanger = pcm.getClass().getMethod("a", int.class);
            viewDistChanger.invoke(pcm, vdist);
        } catch (Exception e) {
            handler(e);
        }
    }
*/
}