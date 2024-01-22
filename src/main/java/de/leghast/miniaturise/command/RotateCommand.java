package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.util.Util;
import org.bukkit.Axis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RotateCommand implements CommandExecutor {

    private Miniaturise main;

    public RotateCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("miniaturise.use")) {
                if (args.length >= 1) {
                    if (args.length == 2) {
                        args[0] = args[0].toUpperCase();
                        try {
                            if (main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())) {
                                main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).rotate(Axis.valueOf(args[0]), Float.parseFloat(args[1]));
                                player.sendMessage(Util.PREFIX + "§aThe placed miniature was rotated by §e" + Float.parseFloat(args[1]) + " degrees §a on the §e" + Axis.valueOf(args[0]).name() + "-axis");
                                return true;
                            } else {
                                player.sendMessage(Util.PREFIX + "§cYou do not have selected a miniature");
                                return false;
                            }
                        } catch (IllegalArgumentException e) {
                            if (e instanceof NumberFormatException) {
                                player.sendMessage(Util.PREFIX + "§cPlease provide a valid angle");
                            } else {
                                player.sendMessage(Util.PREFIX + "§cPlease provide a valid axis (x, y, z)");
                            }
                            return false;
                        }
                    }
                } else {
                    player.sendMessage(Util.PREFIX + "§cUsage /rotate <axis> <angle>");
                }
            }
        }

        return false;
    }
}
