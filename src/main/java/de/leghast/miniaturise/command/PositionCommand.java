package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.region.SelectedLocations;
import de.leghast.miniaturise.util.Util;
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
                if(!main.getRegionManager().hasSelectedLocations(player.getUniqueId())){
                    main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations());    
                }
                switch (args[0]){
                    case "1" -> {
                        main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc1(player.getLocation());
                        player.sendMessage(Util.PREFIX + "§aThe first position was set to §e" +
                                (int) player.getLocation().getX() + ", " +
                                (int) player.getLocation().getY() + ", " +
                                (int) player.getLocation().getZ() + " §a(" +
                                Util.getDimensionName(player.getLocation().getWorld().getEnvironment().name()) + ")");
                    }
                    case "2" -> {
                        main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc2(player.getLocation());
                        player.sendMessage(Util.PREFIX + "§aThe second position was set to §e" +
                                (int) player.getLocation().getX() + ", " +
                                (int) player.getLocation().getY() + ", " +
                                (int) player.getLocation().getZ() + " §a(" +
                                Util.getDimensionName(player.getLocation().getWorld().getEnvironment().name()) + ")");
                    }
                    default -> {
                        player.sendMessage(Util.PREFIX + "§cInvalid position, please use 1 or 2");
                    }
                }
            }else{
                player.sendMessage(Util.PREFIX + "§cPlease specify a valid position");
            }
        }
        return false;
    }
}
