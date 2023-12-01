package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RotateCommand implements CommandExecutor {

    private Miniaturise main;

    public RotateCommand(Miniaturise main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockCount() == 1){
                if(args.length >= 1){
                    if(args.length >= 2){
                        try{
                            double factor = Double.parseDouble(args[2]);
                        }catch (NumberFormatException e){
                            player.sendMessage(main.PREFIX + "§cPlease provide a valid rotation factor");
                            return false;
                        }
                        switch (args[0]){

                        }
                    }else{
                        player.sendMessage(main.PREFIX + "Please provide a valid rotation factor");
                    }
                }else{
                    player.sendMessage(main.PREFIX + "§cUsage: /rotate <axis> <value>");
                }
            }else{
                player.sendMessage(main.PREFIX + "§cYou can only rotate single block displays");
            }
        }
        return false;
    }
}
