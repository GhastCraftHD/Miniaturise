package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.Miniature;
import de.leghast.miniaturise.instance.PlacedMiniature;
import de.leghast.miniaturise.instance.Region;
import de.leghast.miniaturise.manager.ConfigManager;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SelectCommand implements CommandExecutor {

    private Miniaturise main;

    public SelectCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(main.getRegionManager().hasSelectedLocations(player.getUniqueId())){
                if(main.getRegionManager().getSelectedLocations(player.getUniqueId()).isValid()){
                    try{
                        Region region = new Region(main.getRegionManager().getSelectedLocations(player.getUniqueId()));
                        if(main.getRegionManager().hasRegion(player.getUniqueId())) {
                            main.getRegionManager().getRegions().replace(player.getUniqueId(), region);
                        }else{
                            main.getRegionManager().addRegion(player.getUniqueId(), region);
                        }
                        Miniature miniature = new Miniature(region, player.getLocation(), ConfigManager.getDefaultSize());
                        if(miniature.getBlocks().size() >= ConfigManager.getMaxEntityLimit()){
                            player.sendMessage(main.PREFIX + "§cThe current selection §e(" + miniature.getBlocks().size() +" blocks) §cexceeds the limit of §e" + ConfigManager.getMaxEntityLimit() + " §cblocks");
                            main.getRegionManager().removeRegion(player.getUniqueId());
                            return false;
                        }
                        if(main.getMiniatureManager().hasMiniature(player.getUniqueId())){
                            main.getMiniatureManager().getMiniatures().replace(player.getUniqueId(), miniature);
                        }else{
                            main.getMiniatureManager().addMiniature(player.getUniqueId(), miniature);
                        }
                        List<BlockDisplay> blockDisplays = new ArrayList<>();
                        for(Chunk chunk : player.getWorld().getLoadedChunks()){
                            for(Entity entity : chunk.getEntities()){
                                if(entity instanceof BlockDisplay && region.contains(entity.getLocation())){
                                    blockDisplays.add((BlockDisplay) entity);
                                }
                            }
                        }
                        if(!blockDisplays.isEmpty()){
                            if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
                                main.getMiniatureManager().getPlacedMiniatures().replace(player.getUniqueId(), new PlacedMiniature(blockDisplays));
                            }else{
                                main.getMiniatureManager().addPlacedMiniature(player.getUniqueId(), new PlacedMiniature(blockDisplays));
                            }
                        }
                        player.sendMessage(main.PREFIX + "§aThe region was selected §e(" + miniature.getBlocks().size() + " blocks)");
                        return true;
                    }catch(IllegalArgumentException e){
                        player.sendMessage(main.PREFIX + "§c" + e.getMessage());
                        return false;
                    }
                }else{
                    player.sendMessage(main.PREFIX + "§cPlease select two locations");
                }
            }else{
                player.sendMessage(main.PREFIX + "§cYou have not selected any locations");
            }
        }
        return false;
    }
}
