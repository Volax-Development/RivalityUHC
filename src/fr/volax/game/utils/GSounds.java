package fr.volax.game.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GSounds {
    private Player player;

    public GSounds(Player p){
        player = p;
    }

    public void playSound(Sound sound){
        player.playSound(player.getLocation(), sound, 8, 8);
    }
}
