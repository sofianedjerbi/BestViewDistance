package me.lxct.bestviewdistance.functions.async;

import static me.lxct.bestviewdistance.functions.data.Variable.playerLiveViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.playerSettingsViewDistance;
import static me.lxct.bestviewdistance.functions.data.Variable.playerViewDistance;

public class QuitDataUnload implements Runnable {
    private String player;

    public QuitDataUnload(String player) {
        this.player = player;
    }

    @Override
    public void run() {
        playerLiveViewDistance.remove(player);
        playerViewDistance.remove(player);
        playerSettingsViewDistance.remove(player);
    }
}