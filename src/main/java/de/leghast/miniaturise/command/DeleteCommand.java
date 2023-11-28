package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.PlacedMiniature;
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
                if(placedMiniature != null){
                    placedMiniature.removePlacedMiniature();
                    main.getMiniatureManager().getPlacedMiniatures().replace(player.getUniqueId(), null);
                    player.sendMessage(main.PREFIX + "§aThe placed miniature was deleted");
                }else{
                    player.sendMessage(main.PREFIX + "§cYou have not selected a placed miniature");
                }
            }else{
                player.sendMessage(main.PREFIX + "§cYou have not selected a placed miniature");
                return false;
            }
        }
        return false;
    }
}
