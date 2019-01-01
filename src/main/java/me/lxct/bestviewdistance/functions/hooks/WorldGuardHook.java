package me.lxct.bestviewdistance.functions.hooks;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Set;

public class WorldGuardHook {


    public static Set<ProtectedRegion> getPlayerRegions(BVDPlayer p) {
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
            final Location location = p.getLocation();
            final RegionManager rgm = WorldGuard.getInstance().getPlatform().getRegionContainer().get((World) location.getWorld());
            final ApplicableRegionSet ars;
            if (rgm != null) {
                ars = rgm.getApplicableRegions(BlockVector3.at(location.getX(), location.getY(), location.getZ()));
                return ars.getRegions();
            }
        }
        return null;
    }

}