package de.leghast.miniaturise.command;

import de.leghast.miniaturise.manager.MiniatureManager;
import de.leghast.miniaturise.manager.RegionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                MiniatureManager.deleteMiniature();
                player.sendMessage("§aMiniature was deleted");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("selection")) {
                player.sendMessage("§eThis feature is still in development");
            }else{
                player.sendMessage("§cIllegal arguments");
            }
        }
        return false;
    }
}
