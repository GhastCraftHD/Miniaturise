package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.region.SelectedLocations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PositionCommand implements CommandExecutor {

    private final Miniaturise main;

    public PositionCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if(args.length == 0){
            player.sendMessage(Message.SPECIFY_POSITIONS);
            return true;
        }

        if (!main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
            main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations());
        }

        switch (args[0]) {
            case "1" -> {
                main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc1(player.getLocation());
                player.sendMessage(Message.selectedPosition("first", player.getLocation()));
            }
            case "2" -> {
                main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc2(player.getLocation());
                player.sendMessage(Message.selectedPosition("second", player.getLocation()));
            }
            default -> player.sendMessage(Message.INVALID_POSITION);

        }
        return true;
    }
}
