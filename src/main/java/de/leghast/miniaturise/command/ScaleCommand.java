package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.Miniature;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ScaleCommand implements CommandExecutor {

    private Miniaturise main;

    public ScaleCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length >= 1 && (args[0].equalsIgnoreCase("selection") || args[0].equalsIgnoreCase("s"))){
                if(args.length == 2){
                    if(main.getMiniatureManager().hasMiniature(player.getUniqueId())){
                        try{
                            Miniature miniature = main.getMiniatureManager().getMiniature(player.getUniqueId());
                            miniature.scaleMiniature(Double.parseDouble(args[0]));
                            player.sendMessage(main.PREFIX + "§aThe selected miniature was scaled to §e" + miniature.getSize() + " §ablocks");
                            return true;
                        }catch (NumberFormatException e){
                            player.sendMessage(main.PREFIX + "§cPlease provide a valid scale");
                            return false;
                        }
                    }else{
                        player.sendMessage(main.PREFIX + "§cPlease create a miniature first");
                    }
                }
            }else if(args.length >= 1 && (args[0].equalsIgnoreCase("miniature") || args[0].equalsIgnoreCase("m"))){
                if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
                    main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).scalePlacedMiniature();
                }else{
                    player.sendMessage(main.PREFIX + "§cYou have not placed/selected a placed miniature yet");
                }
            }
        }
        return false;
    }
}
