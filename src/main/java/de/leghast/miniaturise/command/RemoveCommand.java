package de.leghast.miniaturise.command;

import de.leghast.miniaturise.manager.MiniatureManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            MiniatureManager.deleteMiniature();
            player.sendMessage("§aMiniature was deleted");
        }
        return false;
    }
}
