package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
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

public class SaveCommand implements CommandExecutor {

    private Miniaturise main;

    public SaveCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(main.getMiniatureManager().hasMiniature(player.getUniqueId())) {
                if (args.length >= 1) {
                    if (args.length == 1) {
                        if (main.getSaveManager().fileExists(args[0])) {
                            player.sendMessage(Util.PREFIX + "§aA miniature with the name §e" + args[0] + ".mcminiature §aalready exists");
                            TextComponent overwrite = Component.text("§e[OVERWRITE]")
                                    .hoverEvent(HoverEvent.showText(Component.text("§aOverwrite §e" + args[0] + ".mcminiature")))
                                    .clickEvent(ClickEvent.runCommand("/msave " + args[0] + " confirm"));
                            player.sendMessage(Component.text(Util.PREFIX + "§aClick here to ").append(overwrite));
                        } else {
                            try {
                                main.getSaveManager().serialize(args[0], main.getMiniatureManager().getMiniature(player.getUniqueId()));
                                player.sendMessage(Util.PREFIX + "§aThe miniature was saved as §e" + args[0] + ".mcminiature");
                            } catch (IOException e) {
                                player.sendMessage(Util.PREFIX + "§cThe miniature could not be saved");
                                e.printStackTrace();
                            }
                        }
                    }else if(args.length == 2){
                        if(args[1].equalsIgnoreCase("confirm")) {
                            try {
                                main.getSaveManager().serialize(args[0], main.getMiniatureManager().getMiniature(player.getUniqueId()));
                                player.sendMessage(Util.PREFIX + "§e" + args[0] + ".mcminiature §awas overwritten");
                            } catch (IOException e) {
                                player.sendMessage(Util.PREFIX + "§cThe miniature could not be saved");
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    player.sendMessage(Util.PREFIX + "§cPlease provide a name for the miniature");
                }
            }else{
                player.sendMessage(Util.PREFIX + "§c/Usage: /msave <filename>");
                player.sendMessage(Util.PREFIX + "§cThe filename may not contain whitespaces");
            }
        }

        return false;
    }

}
