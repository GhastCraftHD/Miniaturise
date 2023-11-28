package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CutCommand implements CommandExecutor {

    private Miniaturise main;

    public CutCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(main.getRegionManager().hasRegion(player.getUniqueId())){
                for(Block block : main.getRegionManager().getRegion(player.getUniqueId()).getBlocks()){
                    block.setType(Material.AIR);
                }
                player.sendMessage(main.PREFIX + "§aThe selected region was cut from the world");
            }else{
                player.sendMessage(main.PREFIX + "§cPlease select a region first");
            }
        }
        return false;
    }
}
