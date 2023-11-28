package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.Miniature;
import de.leghast.miniaturise.instance.Region;
import de.leghast.miniaturise.manager.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SelectCommand implements CommandExecutor {

    private Miniaturise main;

    public SelectCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(main.getRegionManager().getSelectedLocations(player.getUniqueId()).isValid()){
                try{
                    Region region = new Region(main.getRegionManager().getSelectedLocations(player.getUniqueId()));
                    if(region.getBlocks().size() > ConfigManager.getMaxBlockLimit()){
                        player.sendMessage(main.PREFIX + "§cThe selected region exceeds the limit of §e" + ConfigManager.getMaxBlockLimit() + " §cblocks");
                        return false;
                    }
                    if(main.getRegionManager().hasRegion(player.getUniqueId())) {
                        main.getRegionManager().getRegions().replace(player.getUniqueId(), region);
                    }else{
                        main.getRegionManager().addRegion(player.getUniqueId(), region);
                    }
                    if(main.getMiniatureManager().hasMiniature(player.getUniqueId())){
                        main.getMiniatureManager().getMiniatures().replace(player.getUniqueId(), new Miniature(region, player.getLocation(), ConfigManager.getDefaultSize()));
                    }else{
                        main.getMiniatureManager().addMiniature(player.getUniqueId(), new Miniature(region, player.getLocation(), ConfigManager.getDefaultSize()));
                    }
                    player.sendMessage(main.PREFIX + "§aThe region was selected");
                    return true;
                }catch(IllegalArgumentException e){
                    player.sendMessage(main.PREFIX + "§c" + e.getMessage());
                    return false;
                }
            }else{
                player.sendMessage(main.PREFIX + "§cPlease select two locations");
            }
        }
        return false;
    }
}
