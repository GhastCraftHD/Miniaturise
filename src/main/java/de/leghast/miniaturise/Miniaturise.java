package de.leghast.miniaturise;

import de.leghast.miniaturise.command.*;
import de.leghast.miniaturise.listener.SelectionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Miniaturise extends JavaPlugin {

    @Override
    public void onEnable() {

        //EVENTS
        Bukkit.getPluginManager().registerEvents(new SelectionListener(), this);

        //COMMANDS
        getCommand("position").setExecutor(new PositionCommand());
        getCommand("select").setExecutor(new SelectCommand());
        getCommand("miniaturise").setExecutor(new MiniaturiseCommand());
        getCommand("cut").setExecutor(new CutCommand());
        getCommand("paste").setExecutor(new PasteCommand());
        getCommand("remove").setExecutor(new RemoveCommand());
        getCommand("scale").setExecutor(new ScaleCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
