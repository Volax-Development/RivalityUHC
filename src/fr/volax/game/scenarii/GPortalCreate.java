package fr.volax.game.scenarii;

import fr.volax.game.utils.GConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class GPortalCreate implements Listener {
    @EventHandler
    public void playerCreatePortal(PortalCreateEvent event){
        if(!GConfig.PortalCreate()){
            event.setCancelled(true);
        }
    }
}
