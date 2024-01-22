package de.leghast.miniaturise.completer;

import org.bukkit.Axis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RotateTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> results = new ArrayList<>();
        if(args.length == 1){
            for(Axis axis : Axis.values()){
                results.add(axis.name());
            }
            return results;
        }

        return new ArrayList<>();
    }
}
