package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.Miniature;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoadCommand implements TabExecutor {

    private final Miniaturise main;

    public LoadCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(!player.hasPermission(Miniaturise.PERMISSION)) return false;

        if(args.length != 1){
            player.sendMessage(Message.LOAD_COMMAND_USAGE);
            return true;
        }

        if(args[0].endsWith(".mcminiature")){
            args[0] = args[0].replace(".mcminiature", "");
        }

        if(main.getSaveManager().fileExists(args[0])){
            try {
                Miniature miniature = main.getSaveManager().deserialize(args[0]);
                main.getMiniatureManager().addMiniature(player.getUniqueId(), miniature);
                player.sendMessage(Message.loadedMiniatureFromFile(args[0]));
            } catch (Exception e) {
                player.sendMessage(Message.COULD_NOT_LOAD_MINIATURE);
            }
        }else{
            player.sendMessage(Message.couldNotFindFile(args[0]));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> results = new ArrayList<>();

        if(args.length == 1){
            List<File> files = Arrays.stream(main.getSaveManager().getMiniatureFiles()).toList();

            results = files.stream().map(File::getName).toList();
            return StringUtil.copyPartialMatches(args[0], results, new ArrayList<>());
        }

        return results;
    }

}
