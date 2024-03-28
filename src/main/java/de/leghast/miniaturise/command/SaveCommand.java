package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class  SaveCommand implements CommandExecutor {

    private final Miniaturise main;

    public SaveCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if(!main.getMiniatureManager().hasMiniature(player.getUniqueId())){
            player.sendMessage(Message.SAVE_COMMAND_USAGE);
            return true;
        }

        if(args.length > 1){
            player.sendMessage(Message.PROVIDE_FILENAME);
            return true;
        }

        switch(args.length){
            case 1 -> {
                if (main.getSaveManager().fileExists(args[0])) {
                    player.sendMessage(Message.fileAlreadyExists(args[0]));
                    return true;
                }
                try {
                    main.getSaveManager().serialize(args[0], main.getMiniatureManager().getMiniature(player.getUniqueId()));
                    player.sendMessage(Message.savedMiniature(args[0]));
                } catch (IOException e) {
                    player.sendMessage(Message.COULD_NOT_SAVE_MINIATURE);
                }

            }
            case 2 -> {
                if(!args[1].equalsIgnoreCase("confirm")) return false;

                try {
                    main.getSaveManager().serialize(args[0], main.getMiniatureManager().getMiniature(player.getUniqueId()));
                    player.sendMessage(Message.overwroteMiniature(args[0]));
                } catch (IOException e) {
                    player.sendMessage(Message.COULD_NOT_SAVE_MINIATURE);
                }

            }
        }
        return true;
    }

}
