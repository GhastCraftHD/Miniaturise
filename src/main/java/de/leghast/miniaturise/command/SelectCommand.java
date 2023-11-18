package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SelectCommand implements CommandExecutor {

    private Miniaturise main;

    public SelectCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(main.getRegionManager().getLoc1() != null && main.getRegionManager().getLoc2() != null) {
                main.getRegionManager().createRegion(player);
                main.getMiniatureManager().miniaturiseSelection(player.getLocation());
            }else{
                player.sendMessage("§cPlease select two positions");
            }
        }
        return false;
    }
}
