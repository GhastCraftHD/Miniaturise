package de.leghast.miniaturise.command;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.ui.PositionInterface;
import de.leghast.miniaturise.ui.SizeInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AdjustCommand implements CommandExecutor {

    private Miniaturise main;

    private PositionInterface positionInterface;
    private SizeInterface sizeInterface;

    public AdjustCommand(Miniaturise main){
        this.main = main;

        positionInterface = new PositionInterface(this);
        sizeInterface = new SizeInterface(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        return false;
    }

    public Miniaturise getMain(){
        return main;
    }
}
