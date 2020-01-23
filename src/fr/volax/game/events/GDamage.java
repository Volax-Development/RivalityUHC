package fr.volax.game.events;

import fr.volax.game.GMain;
import fr.volax.game.game.GUHCState;
import fr.volax.game.utils.GWorldSounds;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GDamage implements Listener {

    @EventHandler
    public void fakeDeath(PlayerDeathEvent event){
            Player player = event.getEntity();
            fakeDeath(player);

    }

    @EventHandler
    public void fakeDamageDeath(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(GUHCState.isState(GUHCState.WAIT) || GUHCState.isState(GUHCState.PREGAME)){
                event.setCancelled(true);
            }else{
                Player player = (Player) event.getEntity();
                if(player.getHealth() <= event.getDamage()){
                    fakeDeath(player);
                }
            }
        }
    }

    private void respawnInstant (final Player player) {
        Bukkit. getScheduler (). runTaskLater (GMain.getInstance(), new Runnable () {
            @Override
            public void run() {
                PacketPlayInClientCommand paquet = new PacketPlayInClientCommand (PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN);
                ((CraftPlayer) player).getHandle().playerConnection.a(paquet);
            }
        }, 5L);
    }

    private void fakeDeath(Player player) {
            respawnInstant(player);
            player.setGameMode(GameMode.SPECTATOR);
            Bukkit.broadcastMessage(GMain.getInstance().getConfig().getString("messages.death").replace("%death_player%", player.getName()).replaceAll("&","§"));
            GMain.getInstance().playerInGame.remove(player.getUniqueId());
            Bukkit.broadcastMessage(String.valueOf(GMain.getInstance().playerInGame.size()));
            new GWorldSounds(player.getLocation()).playSound(Sound.AMBIENCE_THUNDER);
    }

    @EventHandler
    public void disablePlayerDeathEventMessage(PlayerDeathEvent event){
        event.setDeathMessage(null);
    }
}
