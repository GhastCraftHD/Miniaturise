package de.leghast.miniaturise.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ToolTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> results = new ArrayList<>();
        if(args.length == 1){
            results.add("set");
            results.add("get");
            return StringUtil.copyPartialMatches(args[0], results, new ArrayList<>());
        }else if(args.length == 2){
            results.add("selector");
            results.add("adjuster");
            return StringUtil.copyPartialMatches(args[1], results, new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
