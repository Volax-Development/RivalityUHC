package fr.volax.game.utils;

import fr.volax.game.GMain;
import fr.volax.game.file.FileManager;


public class GConfig {
    public static FileManager configs = new FileManager(GMain.getInstance());

    public GConfig() {}

    // Scenarii.yml
    public static boolean NoFall(){ return configs.getConfig("scenarii.yml").get().getBoolean("nofall"); }
    public static boolean VanillaRegen(){
        return configs.getConfig("scenarii.yml").get().getBoolean("vanillaregen");
    }
    public static boolean PortalCreate(){
        return configs.getConfig("scenarii.yml").get().getBoolean("portalcreate");
    }
    public static boolean FastBreakTree() { return configs.getConfig("scenarii.yml").get().getBoolean("timber"); }

    // Options.yml
    public static int GAppleRegenDuration(){
        return configs.getConfig("options.yml").get().getInt("GApple.duration");
    }
    public static int GAppleRegenAmplifier(){ return configs.getConfig("options.yml").get().getInt("GApple.amplifier"); }

    // Scoreboards.yml
    public static String ScoreboardName1(){ return configs.getConfig("scoreboards.yml").get().getString("scoreboard1.name"); }
    public static String ScoreboardIP(){ return configs.getConfig("scoreboards.yml").get().getString("scoreboard1.ip"); }
}
