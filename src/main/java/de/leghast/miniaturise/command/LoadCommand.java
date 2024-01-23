package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.miniature.Miniature;
import de.leghast.miniaturise.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class LoadCommand implements CommandExecutor {

    private Miniaturise main;

    public LoadCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1){
                if(main.getSaveManager().fileExists(args[0])){
                    try {
                        Miniature miniature = main.getSaveManager().deserialize(args[0]);
                        main.getMiniatureManager().addMiniature(player.getUniqueId(), miniature);
                        player.sendMessage(Util.PREFIX + "§e" + args[0] + " §awas loaded to your clipboard");
                    } catch (Exception e) {
                        player.sendMessage(Util.PREFIX + "§cThe miniature could not be loaded");
                    }
                }else{
                    player.sendMessage(Util.PREFIX + "§cThe file §e" + args[0] + " §ccould not be found");
                }
            }else{
                player.sendMessage(Util.PREFIX + "§cUsage: /mload <filename>");
            }
        }
        return false;
    }
}
