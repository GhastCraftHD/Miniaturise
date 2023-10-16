package de.leghast.miniaturise.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PositionTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> results = new ArrayList<>();
        if(args.length == 1){
            results.add("1");
            results.add("2");
            return StringUtil.copyPartialMatches(args[0], results, new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
