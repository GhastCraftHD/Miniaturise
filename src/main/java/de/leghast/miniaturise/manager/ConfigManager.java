package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * This class manages the config settings for the Miniaturise plugin
 * @author GhastCraftHD
 * */
public class ConfigManager {

    private static FileConfiguration config;

    //Setup/Initialise the ConfigManager
    public static void setupConfig(Miniaturise main){
        ConfigManager.config = main.getConfig();
        main.saveDefaultConfig();
    }

    /**
     * @return The selector tool material
     */
    public static Material getSelectorToolMaterial(){
        return Material.matchMaterial(config.getString("selector-tool"));
    }

    /**
     * @return The maximum amount of blocks that are allowed in one miniature
     */
    public static int getMaxEntityLimit(){
        return config.getInt("max-entity-limit");
    }

    /**
     * @return The default size of a block in a newly instanced miniature
     */
    public static double getDefaultSize(){
        return config.getDouble("default-size");
    }

    public static Material getAdjusterToolMaterial(){
        return Material.matchMaterial(config.getString("adjuster-tool"));
    }

}
