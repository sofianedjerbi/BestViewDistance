package me.lxct.bestviewdistance.functions.async;

import static me.lxct.bestviewdistance.functions.data.Variable.*;

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
        afkList.remove(player);
        flyingList.remove(player);
    }
}