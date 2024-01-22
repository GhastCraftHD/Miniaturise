package de.leghast.miniaturise;

import de.leghast.miniaturise.command.*;
import de.leghast.miniaturise.completer.PositionTabCompleter;
import de.leghast.miniaturise.completer.RotateTabCompleter;
import de.leghast.miniaturise.completer.ScaleTabCompleter;
import de.leghast.miniaturise.completer.ToolTabCompleter;
import de.leghast.miniaturise.listener.InventoryClickListener;
import de.leghast.miniaturise.listener.PlayerQuitListener;
import de.leghast.miniaturise.listener.PlayerInteractListener;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.manager.MiniatureManager;
import de.leghast.miniaturise.manager.RegionManager;
import de.leghast.miniaturise.manager.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main plugin class of the Miniaturise Minecraft paper plugin
 * @author GhastCraftHD
 * */
public final class Miniaturise extends JavaPlugin {

    private MiniatureManager miniatureManager;
    private RegionManager regionManager;
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        initialiseManagers();
        registerListeners();
        setCommands();
        setTabCompleters();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    private void initialiseManagers(){
        miniatureManager = new MiniatureManager(this);
        regionManager = new RegionManager(this);
        settingsManager = new SettingsManager(this);
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
    }

    private void setCommands(){
        getCommand("select").setExecutor(new SelectCommand(this));
        getCommand("scale").setExecutor(new ScaleCommand(this));
        getCommand("cut").setExecutor(new CutCommand(this));
        getCommand("tools").setExecutor(new ToolsCommand(this));
        getCommand("paste").setExecutor(new PasteCommand(this));
        getCommand("delete").setExecutor(new DeleteCommand(this));
        getCommand("copy").setExecutor(new CopyCommand(this));
        getCommand("position").setExecutor(new PositionCommand(this));
        getCommand("clear").setExecutor(new ClearCommand(this));
        getCommand("adjust").setExecutor(new AdjustCommand(this));
        getCommand("rotate").setExecutor(new RotateCommand(this));
    }

    public void setTabCompleters(){
        getCommand("position").setTabCompleter(new PositionTabCompleter());
        getCommand("scale").setTabCompleter(new ScaleTabCompleter());
        getCommand("tool").setTabCompleter(new ToolTabCompleter());
        getCommand("rotate").setTabCompleter(new RotateTabCompleter());
    }

    /**
     * @return The MiniatureManager instance
     */
    public MiniatureManager getMiniatureManager(){
        return miniatureManager;
    }

    /**
     * @return The RegionManager instance
     */
    public RegionManager getRegionManager(){
        return regionManager;
    }

    public SettingsManager getSettingsManager(){
        return settingsManager;
    }

}
