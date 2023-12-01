package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.miniature.PlacedMiniature;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;

public class PasteCommand implements CommandExecutor {

    private Miniaturise main;

    public PasteCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(main.getMiniatureManager().hasMiniature(player.getUniqueId())){
                try{
                    PlacedMiniature placedMiniature;
                    placedMiniature = new PlacedMiniature(main.getMiniatureManager().getMiniature(player.getUniqueId()).getBlocks(), player.getLocation());
                    if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
                        main.getMiniatureManager().getPlacedMiniatures().replace(player.getUniqueId(), placedMiniature);
                    }else{
                        main.getMiniatureManager().addPlacedMiniature(player.getUniqueId(), placedMiniature);
                    }
                    player.sendMessage(main.PREFIX + "§aThe miniature was placed at §e" +
                            (int) player.getLocation().getX() + ", " +
                            (int) player.getLocation().getY() + ", " +
                            (int) player.getLocation().getZ() + " §a(" +
                            main.getDimensionName(player.getLocation().getWorld().getEnvironment().name()) + ") §e(" + placedMiniature.getBlockCount() + " block" + (placedMiniature.getBlockCount() == 1 ? "" : "s") + ")");
                }catch (InvalidParameterException e){
                    player.sendMessage(main.PREFIX + "§cThe miniature in you clipboard contains no blocks");
                }
            }else{
                player.sendMessage(main.PREFIX + "§cPlease create a miniature first");
            }
        }
        return false;
    }
}
