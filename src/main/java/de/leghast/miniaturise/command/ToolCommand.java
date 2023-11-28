package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
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
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1 && args[0].equalsIgnoreCase("set")){
                if(args.length == 2){
                    if(args.length == 3 && args[1].equalsIgnoreCase("selector")){
                        if(Material.matchMaterial(args[2]) != null){
                            ConfigManager.setSelectorToolMaterial(Material.matchMaterial(args[2]));
                            player.sendMessage(main.PREFIX + "§aThe selector tool was set to §eminecraft:" + ConfigManager.getSelectorToolMaterial().name().toLowerCase());
                        }else{
                            player.sendMessage(main.PREFIX + "§cPlease specify a valid item/block");
                        }
                    }else if(args.length == 2){
                        if(!player.getInventory().getItemInMainHand().isEmpty()){
                            ConfigManager.setSelectorToolMaterial(player.getInventory().getItemInMainHand().getType());
                            player.sendMessage(main.PREFIX + "§aThe selector tool was set to §eminecraft:" + ConfigManager.getSelectorToolMaterial().name().toLowerCase());
                        }else{
                            player.sendMessage(main.PREFIX + "§cPlease hold an item in your main hand or specify the item in the command");
                        }
                    }
                }else{
                    player.sendMessage(main.PREFIX + "§cPlease specify a valid tool");
                }
            }else if(args.length == 1 && args[0].equalsIgnoreCase("get")){
                if(args.length == 2 && args[1].equalsIgnoreCase("selector")){

                }
            }
        }
        return false;
    }
}
