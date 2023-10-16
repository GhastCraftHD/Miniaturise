package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToolCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(ConfigManager.getToolMaterial().toString());
                return true;
            } else if (args.length == 1) {
                if(args[0].equals("set")){
                    if (!player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        ConfigManager.setToolMaterial(player.getInventory().getItemInMainHand().getType());
                        player.sendMessage("§aSelection tool was set to " + ConfigManager.getToolMaterial());
                        return true;
                    } else {
                        player.sendMessage("§cPlease hold an item in your main hand");
                        return false;
                    }
                }else{
                    player.sendMessage("§cIllegal argument");
                    return false;
                }
            }
        }



        return false;
    }
}
