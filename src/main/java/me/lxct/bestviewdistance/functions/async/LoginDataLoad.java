package me.lxct.bestviewdistance.functions.async;

import me.lxct.bestviewdistance.functions.data.Variable;
import org.bukkit.entity.Player;

import static me.lxct.bestviewdistance.functions.data.Variable.playerViewDistance;

public class LoginDataLoad implements Runnable {
    private Player player;
    public LoginDataLoad(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if (!playerViewDistance.containsKey(player.getName())) {
            playerViewDistance.put(player.getName(), Variable.min); // LOAD PLAYER DATA
        }
    }
}