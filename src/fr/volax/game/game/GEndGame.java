package fr.volax.game.game;

import fr.volax.game.GMain;
import fr.volax.game.utils.GTitle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GEndGame {
    public static void checkWin(){
        int online = GMain.getInstance().playerInGame.size();

        if(online == 0){
            stopGame(10);
        }
        if(online == 1){
            for(UUID pl : GMain.getInstance().playerInGame){
                Player winner = Bukkit.getPlayer(pl);
                Bukkit.broadcastMessage(GMain.getInstance().getConfig().getString("messages.winner").replaceAll("%winner%", winner.getName()).replaceAll("&", "§"));
                GTitle.sendTitle(winner, GMain.getInstance().getConfig().getString("messageq.winner.gagnat_title.title").replaceAll("&","§"), GMain.getInstance().getConfig().getString("messageq.winner.gagnat_title.subtitle").replaceAll("&","§"), 80);
                stopGame(6);
            }
        }
    }

    public static void stopGame(int timeUntilStop) {
        Bukkit.getScheduler().runTaskLater(GMain.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.kickPlayer(GMain.getInstance().getConfig().getString("messages.end_game"));
                }
                Bukkit.shutdown();
            }
        },timeUntilStop * 20);
    }
}
