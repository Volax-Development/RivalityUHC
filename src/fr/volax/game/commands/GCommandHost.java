package fr.volax.game.commands;

import fr.volax.game.GMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GCommandHost implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){ return false; }
        Player player = ((Player) sender).getPlayer();
        if(!player.hasPermission(GMain.getInstance().getConfig().getString("permissions.host_command"))){
            player.sendMessage(GMain.getInstance().getConfig().getString("messages.pperms").replaceAll("&","§"));
            return false;
        }
        if(cmd.getName().equalsIgnoreCase("hostsay")){
            if(!player.hasPermission(GMain.getInstance().getConfig().getString("permissions.host_command_say"))){
                player.sendMessage(GMain.getInstance().getConfig().getString("messages.pperms").replaceAll("&","§"));
                return false;
            }else{
                if(!(args.length == 0)){
                    Bukkit.broadcastMessage(GMain.getInstance().getConfig().getString("messages.host_say").replace("%host_name%", player.getName()).replace("%message%", ChatColor.translateAlternateColorCodes('&', String.join(" ", args))).replaceAll("&", "§"));
                    return false;
                }else{
                    player.sendMessage(GMain.getInstance().getConfig().getString("messages.help.host_say").replaceAll("&","§"));
                    return false;
                }
            }
        }
        return false;
    }
}
