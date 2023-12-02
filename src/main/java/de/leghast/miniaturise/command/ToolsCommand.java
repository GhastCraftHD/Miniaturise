package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToolsCommand implements CommandExecutor {

    private Miniaturise main;

    public ToolsCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            player.sendMessage(Util.PREFIX + "§aThese items/blocks are currently bound to tools:");
            player.sendMessage(Util.PREFIX + "§a  - Selector: §eminecraft:" + ConfigManager.getSelectorToolMaterial().name().toLowerCase());
            player.sendMessage(Util.PREFIX + "§a  - Adjuster: §eminecraft:" + ConfigManager.getAdjusterToolMaterial().name().toLowerCase());
            return true;
        }
        return false;
    }
}
