package fr.volax.game.game;

import fr.volax.game.GMain;
import fr.volax.game.utils.GBorder;
import fr.volax.game.utils.GMessages;
import fr.volax.game.utils.GTeleport;
import fr.volax.game.utils.GTitle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class GUHCGame {

    private static int task;
    private static int timer = GMain.getInstance().getConfig().getInt("game_time");
    private GMessages MessagesUtils = new GMessages();

    public static void start() {
        for(UUID uuid : GMain.getInstance().playerInGame) {
            Player player = Bukkit.getPlayer(uuid);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));


            Bukkit.getWorld("world").setPVP(false);
            GUHCState.setState(GUHCState.PREGAME);
            GTeleport.tpRandom(player);

            GTitle.sendActionBar(player, GMain.getInstance().getConfig().getString("messages.vulne.debut").replaceAll("&","§"));
            player.sendMessage(GMain.getInstance().getConfig().getString("messages.vulne.debut").replaceAll("&","§"));

            // KITS
            // SCENARIOS
            // TEAM / PAS TEAM
        }
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(GMain.getInstance(), () -> {
                timer--;
                for(UUID uuid : GMain.getInstance().playerInGame) {
                    // fin vulnerabilité
                    if(timer == GMain.getInstance().getConfig().getInt("game_time") - GMain.getInstance().getConfig().getInt("fin_vulne_time")){
                        GUHCState.setState(GUHCState.GAME);
                        GTitle.sendActionBar(player, GMain.getInstance().getConfig().getString("messages.vulne.fin").replaceAll("&","§"));
                        player.sendMessage(GMain.getInstance().getConfig().getString("messages.vulne.fin").replaceAll("&","§"));
                    }

                    // 30 secondes avant debut pvp
                    if(timer == GMain.getInstance().getConfig().getInt("game_time") - GMain.getInstance().getConfig().getInt("debut_pvp") - 30){
                        GTitle.sendActionBar(player, GMain.getInstance().getConfig().getString("messages.pvp.30").replaceAll("&","§"));
                        player.sendMessage(GMain.getInstance().getConfig().getString("messages.pvp.30").replaceAll("&","§"));
                    }

                    // debut pvp
                    if(timer == GMain.getInstance().getConfig().getInt("game_time") - GMain.getInstance().getConfig().getInt("debut_pvp")){
                        GTitle.sendActionBar(player, GMain.getInstance().getConfig().getString("messages.pvp.debut").replaceAll("&","§"));
                        player.sendMessage(GMain.getInstance().getConfig().getString("messages.pvp.debut").replaceAll("&","§"));
                        GUHCState.setState(GUHCState.GAMEPVP);
                        Bukkit.getWorld("world").setPVP(true);
                    }

                    // debut avancement bordure
                    if(timer == GMain.getInstance().getConfig().getInt("game_time") - GMain.getInstance().getConfig().getInt("debut_bordure")){
                        GBorder Bordure = new GBorder();
                        Bordure.reduceBorder(timer);
                    }
                }
            },20,20);
    }
}
