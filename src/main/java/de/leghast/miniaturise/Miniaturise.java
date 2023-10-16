package de.leghast.miniaturise;

import de.leghast.miniaturise.command.*;
import de.leghast.miniaturise.completer.PositionTabCompleter;
import de.leghast.miniaturise.completer.ToolTabCompleter;
import de.leghast.miniaturise.listener.SelectionListener;
import de.leghast.miniaturise.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Miniaturise extends JavaPlugin {

    @Override
    public void onEnable() {

        //EVENTS
        Bukkit.getPluginManager().registerEvents(new SelectionListener(), this);

        //COMMANDS
        getCommand("position").setExecutor(new PositionCommand());
        getCommand("position").setTabCompleter(new PositionTabCompleter());
        getCommand("select").setExecutor(new SelectCommand());
        getCommand("miniaturise").setExecutor(new MiniaturiseCommand());
        getCommand("cut").setExecutor(new CutCommand());
        getCommand("paste").setExecutor(new PasteCommand());
        getCommand("remove").setExecutor(new RemoveCommand());
        getCommand("scale").setExecutor(new ScaleCommand());
        getCommand("tool").setExecutor(new ToolCommand());
        getCommand("tool").setTabCompleter(new ToolTabCompleter());

        //CONFIG
        ConfigManager.setupConfig(this);
    }

    @Override
    public void onDisable() {
        saveConfigFile();
    }

    public void saveConfigFile(){
        this.saveConfig();
    }

}