package de.leghast.miniaturise;

import de.leghast.miniaturise.command.*;
import de.leghast.miniaturise.completer.PositionTabCompleter;
import de.leghast.miniaturise.completer.RotateTabCompleter;
import de.leghast.miniaturise.completer.ScaleTabCompleter;
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
import java.util.function.Consumer;
import java.util.logging.Level;

/**
 * The main plugin class of the Miniaturise Minecraft paper plugin
 * @author GhastCraftHD
 * */
public final class Miniaturise extends JavaPlugin {

    private MiniatureManager miniatureManager;
    private RegionManager regionManager;
    private SettingsManager settingsManager;
    private SaveManager saveManager;

    private String owner = "LeGhast";
    private String repo = "Miniaturise";

    private boolean updateAvailable = false;
    private String latestVersion = getDescription().getVersion();

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        initialiseManagers();
        registerListeners();
        setCommands();
        setTabCompleters();
    }

    private void initialiseManagers(){
        miniatureManager = new MiniatureManager(this);
        regionManager = new RegionManager(this);
        settingsManager = new SettingsManager(this);
        saveManager = new SaveManager(this);
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
        getCommand("mtools").setExecutor(new ToolsCommand(this));
        getCommand("mpaste").setExecutor(new PasteCommand(this));
        getCommand("mremove").setExecutor(new RemoveCommand(this));
        getCommand("mcopy").setExecutor(new CopyCommand(this));
        getCommand("mposition").setExecutor(new PositionCommand(this));
        getCommand("mclear").setExecutor(new ClearCommand(this));
        getCommand("madjust").setExecutor(new AdjustCommand(this));
        getCommand("mrotate").setExecutor(new RotateCommand(this));
        getCommand("msave").setExecutor(new SaveCommand(this));
        getCommand("mload").setExecutor(new LoadCommand(this));
    }

    public void setTabCompleters(){
        getCommand("mposition").setTabCompleter(new PositionTabCompleter());
        getCommand("mscale").setTabCompleter(new ScaleTabCompleter());
        getCommand("mrotate").setTabCompleter(new RotateTabCompleter());
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

    public void checkForUpdateAsync() {
    Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/releases/latest";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    latestVersion = response.toString().contains("tag_name")
                            ? response.toString().split("\"tag_name\":\"")[1].split("\",")[0]
                            : null;

                    updateAvailable = latestVersion != null && !latestVersion.equals("v" + getDescription().getVersion());
                } else {
                    getLogger().log(Level.INFO ,"Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());
                }
            } finally {
                connection.disconnect();
            }
        } catch (Exception e) {
            getLogger().log(Level.INFO, "Couldn't fetch latest version");
        }
    });
}

    public boolean isUpdateAvailable(){
        return updateAvailable;
    }

    public String getLatestReleaseVersion(){
        return latestVersion;
    }

}
