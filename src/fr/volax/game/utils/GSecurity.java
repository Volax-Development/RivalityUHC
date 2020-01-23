package fr.volax.game.utils;

import fr.volax.game.GMain;
import org.bukkit.Bukkit;

public class GSecurity {
    public GSecurity verif(){
        if (!GMain.getInstance().getDescription().getAuthors().contains("Volax")) {
            System.out.println("Le nom de l'auteur a été changé, le plugin se désactive !");
            Bukkit.getPluginManager().disablePlugin(GMain.getInstance());
        }
        return null;
    }
}
