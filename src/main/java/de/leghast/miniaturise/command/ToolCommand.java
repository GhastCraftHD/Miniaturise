package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
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
            if(args.length >= 1 && args[0].equalsIgnoreCase("set")){
                if(args.length >= 2){
                    if(args.length == 3 && args[1].equalsIgnoreCase("selector")){
                        if(Material.matchMaterial(args[2]) != null){
                            ConfigManager.setSelectorToolMaterial(Material.matchMaterial(args[2]));
                            player.sendMessage(main.PREFIX + "§aThe selector tool was set to §eminecraft:" + ConfigManager.getSelectorToolMaterial().name().toLowerCase());
                        }else{
                            player.sendMessage(main.PREFIX + "§cPlease specify a valid item/block");
                        }
                        return true;
                    }else if(args.length == 2){
                        if(player.getInventory().getItemInMainHand().getType() != Material.AIR){
                            if(player.getInventory().getItemInMainHand().getType() != ConfigManager.getSelectorToolMaterial()){
                                ConfigManager.setSelectorToolMaterial(player.getInventory().getItemInMainHand().getType());
                                player.sendMessage(main.PREFIX + "§aThe selector tool was set to §eminecraft:" + ConfigManager.getSelectorToolMaterial().name().toLowerCase());
                            }else{
                                player.sendMessage(main.PREFIX + "§cThe selector tool is already set to §eminecraft:" + ConfigManager.getSelectorToolMaterial().name().toLowerCase());
                            }
                        }else{
                            player.sendMessage(main.PREFIX + "§cPlease hold an item in your main hand or specify the item in the command");
                        }
                    }else{
                        player.sendMessage(main.PREFIX + "§cPlease specify a valid tool");
                    }
                }else{
                    player.sendMessage(main.PREFIX + "§cPlease specify a valid tool");
                }
            }else if(args.length >= 1 && args[0].equalsIgnoreCase("get")){
                if(args.length == 2 && args[1].equalsIgnoreCase("selector")){
                    player.getInventory().addItem(new ItemStack(ConfigManager.getSelectorToolMaterial()));
                    player.sendMessage(main.PREFIX + "§aYou recieved the §eselector §atool");
                }else{
                    player.sendMessage(main.PREFIX + "§cPlease specify a valid tool");
                }
            }else{
                player.sendMessage(main.PREFIX + "§cPlease specify a valid operation");
            }
        }
        return false;
    }
}
