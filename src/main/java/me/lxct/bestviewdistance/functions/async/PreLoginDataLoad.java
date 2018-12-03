package me.lxct.bestviewdistance.functions.async;

import me.lxct.bestviewdistance.functions.data.Variable;

import static me.lxct.bestviewdistance.functions.data.Variable.playerLiveViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.playerViewDistance;

public class PreLoginDataLoad implements Runnable {
    private String player;

    public PreLoginDataLoad(String player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (!playerViewDistance.containsKey(player)) {
            // we set permissions too here
            playerViewDistance.put(player, Variable.onloginview); // LOAD PLAYER DATA
            playerLiveViewDistance.put(player, Variable.onloginview); // LOAD PLAYER DATA
        }
    }
}
