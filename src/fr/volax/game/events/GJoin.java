package fr.volax.game.events;

import fr.volax.game.GMain;
import fr.volax.game.game.GUHCGame;
import fr.volax.game.game.GUHCState;
import fr.volax.game.utils.GBorder;
import fr.volax.game.utils.GMessages;
import fr.volax.game.utils.GTitle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class GJoin implements Listener {

    private static int task;
    public static int timer = GMain.getInstance().getConfig().getInt("start_timer");
    GBorder Bordure = new GBorder();
    private GMessages MessagesUtils = new GMessages();

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(GUHCState.isState(GUHCState.PREGAME) || GUHCState.isState(GUHCState.GAME) || GUHCState.isState(GUHCState.GAMEPVP)){
            event.setJoinMessage(GMain.getInstance().getConfig().getString("messages.join2").replaceAll("&","§").replaceAll("%player%", player.getName()));
            player.sendMessage(GMain.getInstance().getConfig().getString("messages.game_already_start").replaceAll("&","§"));
            player.setGameMode(GameMode.SPECTATOR);
        }else{
            if(GUHCState.isState(GUHCState.WAIT)){
                if(!GMain.getInstance().playerInGame.contains(player.getUniqueId())){
                    Bordure.border();
                    GMain.getInstance().playerInGame.add(player.getUniqueId());
                    event.setJoinMessage(GMain.getInstance().getConfig().getString("messages.join1").replaceAll("&","§").replaceAll("%player%", player.getName()).replaceAll("%player_in_game%", String.valueOf(GMain.getInstance().playerInGame.size())).replaceAll("%max_player%", String.valueOf(Bukkit.getServer().getMaxPlayers())));

                    if(GMain.getInstance().playerInGame.size() >= GMain.getInstance().getConfig().getInt("min_player_to_start")){

                        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(GMain.getInstance(), () -> {
                            timer--;
                            MessagesUtils.messageGameStart(timer);
                            if(GMain.getInstance().playerInGame.size() < GMain.getInstance().getConfig().getInt("min_player_to_start")){
                                timer = GMain.getInstance().getConfig().getInt("timer");
                                player.setLevel(0);
                                System.out.println("Nombre de joueur inferieure aux nombres pour start -> stop du jeu");
                                Bukkit.getScheduler().cancelTask(task);
                                return;
                            }
                            setLevel(timer);
                            if(timer == 0){
                                GUHCGame.start();
                                GTitle.sendTitle(player, GMain.getInstance().getConfig().getString("messages.start.lancer.title2").replaceAll("&","§"), GMain.getInstance().getConfig().getString("messages.start.lancer.subtitle2").replaceAll("%rien%", null).replaceAll("&","§"), 20);
                                Bukkit.getScheduler().cancelTask(task);
                            }
                        },20,20);
                    }
                }
            }
        }
    }

    private void setLevel(int timer){
        for(UUID uuid : GMain.getInstance().playerInGame) {
            Player player = Bukkit.getPlayer(uuid);
            if(timer == 0){
                player.setLevel(0);
                return;
            }
            player.setLevel(timer);
            return;
        }
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(GMain.getInstance().playerInGame.contains(player)) GMain.getInstance().playerInGame.remove(player.getUniqueId());

        if(GUHCState.isState(GUHCState.PREGAME) || GUHCState.isState(GUHCState.GAME) || GUHCState.isState(GUHCState.GAMEPVP)){
            event.setQuitMessage(GMain.getInstance().getConfig().getString("messages.quit2").replaceAll("&","§").replaceAll("%player%", player.getName()));
        }else{
            event.setQuitMessage(GMain.getInstance().getConfig().getString("messages.quit1").replaceAll("&","§").replaceAll("%player%", player.getName()).replaceAll("%player_in_game%", String.valueOf(GMain.getInstance().playerInGame.size())).replaceAll("%max_player%", String.valueOf(Bukkit.getServer().getMaxPlayers())));
        }
    }
}
