package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.region.Region;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CutCommand implements CommandExecutor {

    private final Miniaturise main;

    public CutCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        Bukkit.getScheduler().runTaskAsynchronously(main, () -> {

            if (!main.getRegionManager().hasRegion(player.getUniqueId())) {
                player.sendMessage(Message.SELECT_REGION_FIRST);
                return;
            }

            Region region = main.getRegionManager().getRegion(player.getUniqueId());

            region.getBlocks()
                    .forEach(block -> {
                        block.setType(Material.AIR);
                    });

            player.sendMessage(Message.cutRegion(region.getBlocks().size()));

        });

        return true;
    }
}
