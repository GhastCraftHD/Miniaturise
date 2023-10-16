package de.leghast.miniaturise.command;

import de.leghast.miniaturise.manager.RegionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PositionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length <= 0){
                player.sendMessage("§cPlease provide the location, you want to set (1 or 2)");
                return false;
            }
            if(args.length == 1){
                switch (args[0]){
                    case "1":
                        RegionManager.setLoc1(player.getLocation(), player);
                        return true;
                    case "2":
                        RegionManager.setLoc2(player.getLocation(), player);
                        return true;
                    default:
                        player.sendMessage("§cInvalid position, please use 1 or 2");
                        return false;
                }
            }
        }
        return false;
    }
}
