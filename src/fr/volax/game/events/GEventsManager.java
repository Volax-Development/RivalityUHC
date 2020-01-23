package fr.volax.game.events;

import fr.volax.game.GMain;
import fr.volax.game.scenarii.GAutoLeafDelay;
import fr.volax.game.scenarii.GPortalCreate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class GEventsManager {
    public static void registerEvents(GMain instance) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GJoin(), instance);
        pm.registerEvents(new GDamage(), instance);
        pm.registerEvents(new GRegen(), instance);
        pm.registerEvents(new GPortalCreate(), instance);
        pm.registerEvents(new GAutoLeafDelay(), instance);
    }
}
