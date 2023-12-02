package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.util.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class ToolCommand implements CommandExecutor {

    private Miniaturise main;

    public ToolCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length >= 2 && args[0].equalsIgnoreCase("set")){
                switch (args[1]){
                    case "selector" -> {
                        if(args.length == 3 && Material.matchMaterial(args[2]) != null){
                            ConfigManager.setSelectorToolMaterial(Material.matchMaterial(args[2]));
                            player.sendMessage(Util.PREFIX + "§aThe selector tool was set to §eminecraft:" +
                                    ConfigManager.getSelectorToolMaterial().name().toLowerCase());    
                        }else{
                            player.sendMessage(Util.PREFIX + "§cPlease specify a valid item/block");
                        }
                        if(args.length == 2 && player.getInventory().getItemInMainHand().getType().isAir()){
                            if(player.getInventory().getItemInMainHand().getType() != ConfigManager.getSelectorToolMaterial()){
                                ConfigManager.setSelectorToolMaterial(player.getInventory().getItemInMainHand().getType());
                                player.sendMessage(Util.PREFIX + "§aThe selector tool was set to §eminecraft:" +
                                        ConfigManager.getSelectorToolMaterial().name().toLowerCase());
                            }else{
                                player.sendMessage(Util.PREFIX + "§cThe selector tool is already set to §eminecraft:" +
                                        ConfigManager.getSelectorToolMaterial().name().toLowerCase());
                            }    
                        }else{
                            player.sendMessage(Util.PREFIX + "§cPlease hold an item in your main hand or specify the item in the command");    
                        }
                    }
                    case "adjuster" -> {
                        
                    }
                    default -> {
                        player.sendMessage(Util.PREFIX + "§cPlease specify a valid tool");
                    }
                }
            }else if(args.length == 2 && args[0].equalsIgnoreCase("get")){
                switch (args[1]){
                    case "selector" -> {
                        player.getInventory().addItem(new ItemStack(ConfigManager.getSelectorToolMaterial()));
                        player.sendMessage(Util.PREFIX + "§aYou recieved the §eselector §atool");    
                    }
                    case "adjuster" -> {
                        player.getInventory().addItem(new ItemStack(ConfigManager.getAdjusterToolMaterial()));
                        player.sendMessage(Util.PREFIX + "§aYou recieved the §eadjuster §atool");
                    }
                }   
            }else{
                player.sendMessage(Util.PREFIX + "§cUsage /tool <set/get> <tool>");
            }
        }
        return false;
    }
}
