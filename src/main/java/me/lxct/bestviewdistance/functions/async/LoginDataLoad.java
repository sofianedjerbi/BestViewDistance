package me.lxct.bestviewdistance.functions.async;

        import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.*;

public class LoginDataLoad implements Runnable {
    private Player player;

    public LoginDataLoad(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        // we set permissions too here
        if (useLoginView) {
            playerViewDistance.put(player.getName(), onLoginView); // LOAD PLAYER DATA
            playerLiveViewDistance.put(player.getName(), onLoginView); // LOAD PLAYER DATA
        } else {
            playerViewDistance.put(player.getName(), min); // LOAD PLAYER DATA
            playerLiveViewDistance.put(player.getName(), min); // LOAD PLAYER DATA
        }
    }
}