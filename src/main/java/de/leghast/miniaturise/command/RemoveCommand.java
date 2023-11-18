package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.MiniatureManager;
import de.leghast.miniaturise.manager.RegionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveCommand implements CommandExecutor {

    private Miniaturise main;

    public RemoveCommand(Miniaturise main){
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                main.getMiniatureManager().deleteMiniature();
                player.sendMessage("§aMiniature was deleted");
            }else{
                player.sendMessage("§cIllegal arguments");
            }
        }
        return false;
    }
}
