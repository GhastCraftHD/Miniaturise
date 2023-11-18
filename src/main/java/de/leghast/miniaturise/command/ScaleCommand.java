package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.MiniatureManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ScaleCommand implements CommandExecutor {

    private Miniaturise main;

    public ScaleCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 1){
                main.getMiniatureManager().scaleMiniature(Double.parseDouble(args[0]), player);
                player.sendMessage("§aSelection was scaled");
                return true;
            }else{
                player.sendMessage("§cIllegal Arguments");
                return false;
            }
        }

        return false;
    }
}
