package fr.volax.game.utils;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GWorldSounds {
    private Location loc;

    public GWorldSounds(Location l){
        loc = l;
    }

    public void playSound(Sound sound){
        loc.getWorld().playSound(loc, sound, 8, 8);
    }
}
