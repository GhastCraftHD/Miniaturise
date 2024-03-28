package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.PlacedMiniature;
import org.bukkit.Axis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RotateCommand implements CommandExecutor {

    private final Miniaturise main;

    public RotateCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if (args.length <= 1){
            player.sendMessage(Message.ROTATE_COMMAND_USAGE);
            return true;
        }

        args[0] = args[0].toUpperCase();

        if(!main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
            player.sendMessage(Message.SELECT_PLACED_MINIATURE_FIRST);
            return false;
        }

        PlacedMiniature placedMiniature = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId());

        try {
            placedMiniature.rotate(Axis.valueOf(args[0]), Float.parseFloat(args[1]));
            player.sendMessage(Message.rotatedPlacedMiniature(Float.parseFloat(args[1]), Axis.valueOf(args[0])));
            return true;
        } catch (IllegalArgumentException e) {
            if (e instanceof NumberFormatException) {
                player.sendMessage(Message.INVALID_ANGLE);
            } else {
                player.sendMessage(Message.INVALID_AXIS);
            }
            return false;
        }

    }
}
