package fr.volax.game.utils;

import fr.volax.game.GMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

public class GBorder {
    static World world = Bukkit.getWorld("world");
    static WorldBorder wb = world.getWorldBorder();
    public GBorder border(){
        wb.setCenter(0, 0);
        wb.setSize(GMain.getInstance().getConfig().getInt("bordure.debut_de_jeu.taille") * 2);
        return null;
    }

    public GBorder reduceBorder(int timer){
        Bukkit.getScheduler().runTaskTimer(GMain.getInstance(), new BukkitRunnable(){
            @Override
            public void run() {
                int bordure = GMain.getInstance().getConfig().getInt("bordure.debut_de_jeu.taille") - GMain.getInstance().getConfig().getInt("bordure.fin.taille");
                wb.setSize(wb.getSize() - ((float) bordure / timer) /2);

                if(wb.getSize() == GMain.getInstance().getConfig().getInt("bordure.fin.taille")){
                    cancel();
                }
            }
        }, 0,10);
        return null;
    }

    public static double getBorder(){
        return (int) world.getWorldBorder().getSize();
    }
}
