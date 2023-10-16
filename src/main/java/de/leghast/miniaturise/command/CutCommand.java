package de.leghast.miniaturise.command;

import de.leghast.miniaturise.manager.RegionManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            for (Block block : RegionManager.region.getBlocks()) {
                block.setType(Material.AIR);
            }
            player.sendMessage("§aThe selection was cut");
        }
        return false;
    }
}
