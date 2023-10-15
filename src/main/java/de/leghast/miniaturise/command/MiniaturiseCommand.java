package de.leghast.miniaturise.command;

import de.leghast.miniaturise.miniature.MiniatureManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MiniaturiseCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            MiniatureManager.miniaturiseSelection(player.getLocation());
            player.sendMessage("§aSelection was miniaturised");
        }

        return false;
    }
}
