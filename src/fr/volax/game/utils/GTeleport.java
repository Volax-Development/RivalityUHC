package fr.volax.game.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

public class GTeleport {
    public static void tpRandom(Player player) {
        Random random = new Random();

        int x = random.nextInt(1500);
        int y = 128;
        int z = random.nextInt(1500);
        World world = player.getWorld();

        Location randomLoc = new Location(world,x,y,z);
        player.teleport(randomLoc);
    }
}
