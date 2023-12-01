package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.region.SelectedLocations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PositionCommand implements CommandExecutor {

    private Miniaturise main;

    public PositionCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1){
                switch (args[0]){
                    case "1" -> {
                        if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                            main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc1(player.getLocation());
                        } else {
                            main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(player.getLocation(), null));
                        }
                        player.sendMessage(main.PREFIX + "§aThe first position was set to §e" +
                                (int) player.getLocation().getX() + ", " +
                                (int) player.getLocation().getY() + ", " +
                                (int) player.getLocation().getZ() + " §a(" +
                                main.getDimensionName(player.getLocation().getWorld().getEnvironment().name()) + ")");
                    }
                    case "2" -> {
                        if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                            main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc2(player.getLocation());
                        } else {
                            main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(null, player.getLocation()));
                        }
                        player.sendMessage(main.PREFIX + "§aThe second position was set to §e" +
                                (int) player.getLocation().getX() + ", " +
                                (int) player.getLocation().getY() + ", " +
                                (int) player.getLocation().getZ() + " §a(" +
                                main.getDimensionName(player.getLocation().getWorld().getEnvironment().name()) + ")");
                    }
                    default -> {
                        player.sendMessage(main.PREFIX + "§cInvalid position, please use 1 or 2");
                    }
                }
            }else{
                player.sendMessage(main.PREFIX + "§cPlease specify a valid position");
            }
        }
        return false;
    }
}
