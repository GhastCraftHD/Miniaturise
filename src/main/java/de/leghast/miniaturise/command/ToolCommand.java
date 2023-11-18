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

    private Miniaturise main;

    public ToolCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage("§aCurrent selection tool: §eminecraft:" + main.getConfigManager().getToolMaterial().toString().toLowerCase());
                return true;
            } else if (args.length >= 1) {
                if(args[0].equalsIgnoreCase("set")){
                    if (!player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        main.getConfigManager().setToolMaterial(player.getInventory().getItemInMainHand().getType());
                        player.sendMessage("§aSelection tool was set to §eminecraft:" + main.getConfigManager().getToolMaterial().toString().toLowerCase());
                        return true;
                    }  else {
                        player.sendMessage("§cPlease hold an item in your main hand or specify the item in the command");
                        return false;
                    }
                }else{
                    player.sendMessage("§cIllegal arguments");
                    return false;
                }
            }
        }



        return false;
    }
}
