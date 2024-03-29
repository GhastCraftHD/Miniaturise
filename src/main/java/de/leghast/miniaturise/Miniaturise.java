package de.leghast.miniaturise;

import de.leghast.miniaturise.command.*;
import de.leghast.miniaturise.listener.InventoryClickListener;
import de.leghast.miniaturise.listener.PlayerJoinListener;
import de.leghast.miniaturise.listener.PlayerQuitListener;
import de.leghast.miniaturise.listener.PlayerInteractListener;
import de.leghast.miniaturise.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * The main plugin class of the Miniaturise plugin
 * @author GhastCraftHD
 * */
public final class Miniaturise extends JavaPlugin {

    public static Logger logger;
    public static final String PERMISSION = "miniaturise.use";

    private MiniatureManager miniatureManager;
    private RegionManager regionManager;
    private SettingsManager settingsManager;
    private SaveManager saveManager;

    private boolean updateAvailable = false;
    private String latestVersion = this.getPluginMeta().getVersion();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        initialiseManagers();
        registerListeners();
        setCommands();
        Miniaturise.logger = this.getLogger();
    }

    @Override
    public void onDisable(){
        this.saveConfig();
    }

    private void initialiseManagers(){
        miniatureManager = new MiniatureManager();
        regionManager = new RegionManager();
        settingsManager = new SettingsManager();
        saveManager = new SaveManager(this);
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            ConfigManager.setUpConfig(this);
            checkForUpdate();
        });
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    private void setCommands(){
        getCommand("mselect").setExecutor(new SelectCommand(this));
        getCommand("mscale").setExecutor(new ScaleCommand(this));
        getCommand("mcut").setExecutor(new CutCommand(this));
        getCommand("mtools").setExecutor(new ToolsCommand());
        getCommand("mpaste").setExecutor(new PasteCommand(this));
        getCommand("mremove").setExecutor(new RemoveCommand(this));
        getCommand("mcopy").setExecutor(new CopyCommand(this));
        getCommand("mposition").setExecutor(new PositionCommand(this));
        getCommand("mclear").setExecutor(new ClearCommand(this));
        getCommand("madjust").setExecutor(new AdjustCommand(this));
        getCommand("mrotate").setExecutor(new RotateCommand(this));
        getCommand("msave").setExecutor(new SaveCommand(this));
        getCommand("mload").setExecutor(new LoadCommand(this));
        getCommand("mlist").setExecutor(new ListCommand(this));
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

    public SaveManager getSaveManager(){
        return saveManager;
    }

    private void checkForUpdate() {
        if (!ConfigManager.CHECK_FOR_UPDATE) return;

        String apiUrl = "https://api.github.com/repos/GhastCraftHD/Miniaturise/releases/latest";

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                try {
                    connection.setRequestProperty("Content-Type", "application/json");

                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) return;

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String input;
                    StringBuilder response = new StringBuilder();

                    while ((input = in.readLine()) != null) {
                        response.append(input);
                    }

                    in.close();
                    connection.disconnect();

                    if (!response.toString().contains("tag_name")) return;

                    latestVersion = response.toString().split("\"tag_name\":\"v")[1].split("\",")[0];

                } finally {
                    connection.disconnect();
                }

            } catch (Exception ignore) {}

            updateAvailable = latestVersion != this.getPluginMeta().getVersion();
        });

    }

    public boolean isUpdateAvailable(){
        return updateAvailable;
    }

    public String getLatestReleaseVersion(){
        return latestVersion;
    }

}
