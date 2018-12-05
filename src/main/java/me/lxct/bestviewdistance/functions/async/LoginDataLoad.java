package me.lxct.bestviewdistance.functions.async;

        import me.lxct.bestviewdistance.BestViewDistance;
import me.lxct.bestviewdistance.functions.sync.SetViewDistance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.Set.setPlayerPermissions;
import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class LoginDataLoad implements Runnable {
    private Player player;

    public LoginDataLoad(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        // we set permissions too here
        playerViewDistance.put(player.getName(), onloginview); // LOAD PLAYER DATA
        playerLiveViewDistance.put(player.getName(), onloginview); // LOAD PLAYER DATA
        Bukkit.getScheduler().runTask(BestViewDistance.plugin, new SetViewDistance(player, setPlayerPermissions(player, onloginview))); // Break Async chain
    }
}