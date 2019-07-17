package me.lxct.bestviewdistance.functions.hooks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Set;

public class WorldGuardHook {

    public static String getPlayerRegions(final BVDPlayer p) {
        if (Bukkit.getPluginManager().getPlugin("WorldGuard") != null) {
            if(!Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WorldGuard")).getDescription().getVersion().contains("7")) return null;
            final Player player = p.getPlayer();
            final World world = BukkitAdapter.adapt(player.getWorld());
            final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            final RegionManager regions = container.get(world);
            final int x = player.getLocation().getBlockX();
            final int y = player.getLocation().getBlockY();
            final int z = player.getLocation().getBlockZ();
            if (regions != null) {
                final ApplicableRegionSet apRegions = regions.getApplicableRegions(BlockVector3.at(x, y, z));
                final Set<ProtectedRegion> set = apRegions.getRegions();
                return set.iterator().next().getId(); // name = set.iterator().next().getId();
            }
            return null;
        }
        return null;
    }
}
