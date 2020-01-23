package fr.volax.game.utils;

import fr.volax.game.GMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GMessages {
    public GMessages messageGameStart(int timer){
        for(UUID uuid : GMain.getInstance().playerInGame){
            Player player = Bukkit.getPlayer(uuid);
            if(timer == 30 || timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
                GTitle.sendTitle(player, GMain.getInstance().getConfig().getString("messages.start.en-cours.title1").replaceAll("%timer%", String.valueOf(timer)).replaceAll("&","§"), GMain.getInstance().getConfig().getString("messages.start.en-cours.subtitle1").replaceAll("%timer%", String.valueOf(timer)).replaceAll("&","§"), 20);
            }
        }
        return null;
    }
}
