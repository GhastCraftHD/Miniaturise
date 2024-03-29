package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.Miniature;
import de.leghast.miniaturise.miniature.PlacedMiniature;
import de.leghast.miniaturise.region.Region;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CopyCommand implements CommandExecutor {

    private final Miniaturise main;

    public CopyCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {

            Region region = new Region(main.getRegionManager().getSelectedLocations(player.getUniqueId()));
            main.getRegionManager().addRegion(player.getUniqueId(), region);

            List<BlockDisplay> blockDisplays = main.getRegionManager().getBlockDisplaysFromRegion(player, region);

            if(blockDisplays.isEmpty()){
                player.sendMessage(Message.NO_MINIATURE_IN_REGION);
            }

            Miniature miniature = new Miniature(new PlacedMiniature(blockDisplays), player.getLocation(),
                    blockDisplays.get(0).getTransformation().getScale().x());

            main.getMiniatureManager().addMiniature(player.getUniqueId(), miniature);

            player.sendMessage(Message.copiedPlacedMiniature(miniature.getBlockCount()));

        });

        return true;
    }
}
