package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.PlacedMiniature;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveCommand implements CommandExecutor {

    private final Miniaturise main;

    public RemoveCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if(!main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
            player.sendMessage(Message.SELECT_PLACED_MINIATURE_FIRST);
            return true;
        }

        PlacedMiniature placedMiniature = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId());
        int deletedEntities = placedMiniature.getBlockCount();
        placedMiniature.remove();
        main.getMiniatureManager().removePlacedMiniature(player.getUniqueId());
        player.sendMessage(Message.removedPlacedMiniature(deletedEntities));

        return true;
    }
}
