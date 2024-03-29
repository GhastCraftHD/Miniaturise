package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ListCommand implements CommandExecutor {

    private final Miniaturise main;

    public ListCommand(Miniaturise main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        List<File> files = Arrays.stream(main.getSaveManager().getMiniatureFiles()).toList();

        if(files.isEmpty()){
            player.sendMessage(Message.NO_MINIATURE_FILES);
            return true;
        }

        player.sendMessage(Message.FILE_LIST_HEADER);

        for(File file : files){
            player.sendMessage(Message.miniatureFileComponent(file.getName()));
        }

        return true;
    }
}
