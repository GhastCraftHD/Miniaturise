package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.Miniature;
import de.leghast.miniaturise.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ScaleCommand implements CommandExecutor {

    private final Miniaturise main;

    public ScaleCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if (args.length >= 1 && (args[0].equalsIgnoreCase("selection") || args[0].equalsIgnoreCase("s"))) {

            if(args.length != 2){
                player.sendMessage(Message.INVALID_SCALE);
                return false;
            }

            if(!main.getMiniatureManager().hasMiniature(player.getUniqueId())){
                player.sendMessage(Message.SELECT_MINIATURE_FIRST);
                return false;
            }

            try {
                Miniature miniature = main.getMiniatureManager().getMiniature(player.getUniqueId());
                miniature.scaleUp(Double.parseDouble(args[1]));
                player.sendMessage(Message.scaledMiniature(miniature.getSize()));
                return true;
            } catch (NumberFormatException e) {
                player.sendMessage(Message.INVALID_SCALE);
                return false;
            }

        } else if (args.length >= 1 && (args[0].equalsIgnoreCase("miniature") || args[0].equalsIgnoreCase("m"))) {
            if(args.length != 2){
                player.sendMessage(Message.INVALID_SCALE);
                return false;
            }

            try {
                if (main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())) {
                    main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).scaleUp(Double.parseDouble(args[1]));
                    return true;
                } else {
                    player.sendMessage(Message.SELECT_PLACED_MINIATURE_FIRST);
                    return false;
                }
            } catch (NumberFormatException e) {
                player.sendMessage(Message.INVALID_SCALE);
                return false;
            }
        } else {
            player.sendMessage(Message.SPECIFY_WHAT_TO_SCALE);
        }
        return false;
    }
}
