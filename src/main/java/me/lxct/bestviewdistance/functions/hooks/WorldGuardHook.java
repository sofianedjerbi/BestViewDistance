package me.lxct.bestviewdistance.functions.hooks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.lxct.bestviewdistance.functions.BVDPlayer;

import org.bukkit.Bukkit;

import java.util.Set;

public class WorldGuardHook {

    public static Set<ProtectedRegion> getPlayerRegions(BVDPlayer p) {
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
            final World w = BukkitAdapter.adapt(p.getLocation().getWorld());
            final RegionManager rgm = WorldGuard.getInstance().getPlatform().getRegionContainer().get(w);
            final ApplicableRegionSet ars;
            if (rgm != null) {
                ars = rgm.getApplicableRegions(BukkitAdapter.asBlockVector(p.getLocation()));
                return ars.getRegions();
            }
        }

        return null;
    }
}
