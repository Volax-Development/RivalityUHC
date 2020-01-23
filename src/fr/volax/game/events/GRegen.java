package fr.volax.game.events;

import fr.volax.game.utils.GConfig;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GRegen implements Listener {
    @EventHandler
    public void regainHealth(EntityRegainHealthEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player)event.getEntity();
            if(event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN){
                if(!GConfig.VanillaRegen()){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void eat(PlayerItemConsumeEvent event){
        if(event.getItem().getType().equals(Material.GOLDEN_APPLE)){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, GConfig.GAppleRegenDuration(), GConfig.GAppleRegenAmplifier()));
        }
    }
}
