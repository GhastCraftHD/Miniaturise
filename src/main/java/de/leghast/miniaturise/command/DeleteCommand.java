package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.miniature.PlacedMiniature;
import de.leghast.miniaturise.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DeleteCommand implements CommandExecutor {

    private Miniaturise main;

    public DeleteCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(main.getMiniatureManager().hasMiniature(player.getUniqueId())){
                PlacedMiniature placedMiniature;
                placedMiniature = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId());
                int deletedEntities = 0;
                if(placedMiniature != null){
                    deletedEntities = placedMiniature.getBlockCount();
                    placedMiniature.remove();
                    main.getMiniatureManager().getPlacedMiniatures().replace(player.getUniqueId(), null);
                    player.sendMessage(Util.PREFIX + "§aThe placed miniature was deleted §e(" + deletedEntities +
                            " block" + (deletedEntities == 1 ? "" : "s") + ")");
                }else{
                    player.sendMessage(Util.PREFIX + "§cYou have not selected a placed miniature");
                }
            }else{
                player.sendMessage(Util.PREFIX + "§cYou have not selected a placed miniature");
                return false;
            }
        }
        return false;
    }
}
