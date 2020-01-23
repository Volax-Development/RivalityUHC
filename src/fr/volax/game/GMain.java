package fr.volax.game;

import fr.volax.game.commands.GCommandHost;
import fr.volax.game.commands.GCommandStart;
import fr.volax.game.game.GUHCState;
import fr.volax.game.events.GEventsManager;
import fr.volax.game.utils.GConfig;
import fr.volax.game.utils.GSecurity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/*
    UHC Plugin
    @author Volax
 */
public class GMain extends JavaPlugin {
    public static GMain instance;
    GSecurity Security = new GSecurity();
    public ArrayList<UUID> playerInGame = new ArrayList<>();

    public static GMain getInstance() { return instance; }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Security.verif();

        GUHCState.setState(GUHCState.WAIT);
        GEventsManager.registerEvents(this);

        GConfig.configs.getConfig("scenarii.yml").saveDefaultConfig();
        GConfig.configs.getConfig("options.yml").saveDefaultConfig();
        GConfig.configs.getConfig("scoreboards.yml").saveDefaultConfig();

        getCommand("hostsay").setExecutor(new GCommandHost());
        getCommand("start").setExecutor(new GCommandStart());
    }
}