package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.Miniature;
import de.leghast.miniaturise.miniature.PlacedMiniature;
import de.leghast.miniaturise.region.Region;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.region.SelectedLocations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SelectCommand implements CommandExecutor {

    private final Miniaturise main;

    public SelectCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if(!main.getRegionManager().hasSelectedLocations(player.getUniqueId())){
            player.sendMessage(Message.SELECT_LOCATIONS_FIRST);
            return true;
        }

        SelectedLocations locations = main.getRegionManager().getSelectedLocations(player.getUniqueId());

        if(!locations.isValid()){
            player.sendMessage(Message.INVALID_LOCATIONS);
            return true;
        }

        Region region = new Region(locations);

        main.getRegionManager().addRegion(player.getUniqueId(), region);

        Miniature miniature = new Miniature(region, player.getLocation(), ConfigManager.DEFAULT_SIZE);

        if(miniature.getBlocks().size() >= ConfigManager.MAX_ENTITY_LIMIT){
            player.sendMessage(Message.aboveEntityLimit(miniature.getBlockCount()));
            main.getRegionManager().removeRegion(player.getUniqueId());
            return false;
        }

        main.getMiniatureManager().addMiniature(player.getUniqueId(), miniature);

        List<BlockDisplay> blockDisplays = main.getRegionManager().getBlockDisplaysFromRegion(player, region);

        if(!blockDisplays.isEmpty()){
            main.getMiniatureManager().addPlacedMiniature(player.getUniqueId(), new PlacedMiniature(blockDisplays));
        }

        player.sendMessage(Message.selectedRegion(miniature.getBlockCount()));
        return true;

    }
}
