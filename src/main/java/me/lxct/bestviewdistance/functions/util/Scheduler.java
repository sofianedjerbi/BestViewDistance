package me.lxct.bestviewdistance.functions.util;

import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.BVDPlayer;
import org.bukkit.Bukkit;

import static me.lxct.bestviewdistance.functions.data.Variable.useTasks;

public class Scheduler {
    public static void scheduleSync(Runnable runnable, int delay) {
        final int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, runnable, delay); // Break Async chain
        if (task == -1 && useTasks) {
            Bukkit.getScheduler().runTaskLater(BestViewDistance.plugin, runnable, delay); // Break Async chain
        }
    }

    public static void scheduleSync(Runnable runnable) {
        final int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, runnable); // Break Async chain
        if (task == -1 && useTasks) {
            Bukkit.getScheduler().runTask(BestViewDistance.plugin, runnable); // Break Async chain
        }
    }

    public static void scheduleSync(Runnable runnable, int delay, BVDPlayer player) {
        final int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, runnable, delay); // Break Async chain
        if (task == -1 && useTasks) {
            Bukkit.getScheduler().runTaskLater(BestViewDistance.plugin, runnable, delay); // Break Async chain
        } else {
            player.setTaskId(task);
        }
    }

    public static void scheduleSync(Runnable runnable, BVDPlayer player) {
        final int task = Bukkit.getScheduler().scheduleSyncDelayedTask(BestViewDistance.plugin, runnable); // Break Async chain
        if (task == -1 && useTasks) {
            Bukkit.getScheduler().runTask(BestViewDistance.plugin, runnable); // Break Async chain
        } else {
            player.setTaskId(task);
        }
    }
}
