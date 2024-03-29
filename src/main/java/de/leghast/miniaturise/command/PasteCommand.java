package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.Miniature;
import de.leghast.miniaturise.miniature.PlacedMiniature;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PasteCommand implements CommandExecutor {

    private final Miniaturise main;

    public PasteCommand(Miniaturise main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if (!main.getMiniatureManager().hasMiniature(player.getUniqueId())) {
            player.sendMessage(Message.SELECT_MINIATURE_FIRST);
            return true;
        }

        Miniature miniature = main.getMiniatureManager().getMiniature(player.getUniqueId());

        if(miniature.getBlockCount() <= 0){
            player.sendMessage(Message.MINIATURE_CONTAINS_NO_BLOCKS);
            return true;
        }

        PlacedMiniature placedMiniature;
        placedMiniature = new PlacedMiniature(main.getMiniatureManager().getMiniature(player.getUniqueId()).getBlocks(), player.getLocation());

        main.getMiniatureManager().addPlacedMiniature(player.getUniqueId(), placedMiniature);

        player.sendMessage(Message.placedMiniature(player.getLocation(), main.getMiniatureManager().getMiniature(player.getUniqueId()).getBlockCount()));

        return true;
    }
}
