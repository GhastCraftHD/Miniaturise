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
     * Set a new material as the selector tool
     *
     * @param material - The material, you want the selector tool to be
     */
    public static void setSelectorToolMaterial(Material material){
        config.set("selector-tool", material.name());

    }

    /**
     * @return The maximum amount of blocks that are allowed in one miniature
     */
    public static int getMaxEntityLimit(){
        return config.getInt("max-entity-limit");
    }

    /**
     * Set the maximum amount of blocks that are allowed in one miniature
     * NOTE: Higher numbers than the default value(1500) can lead to various performance issues. Handle with care and responsibility
     *
     * @param maxBlockLimit The new maximum amount of blocks that are allowed in one miniature
     */
    public static void setMaxEntityLimit(int maxBlockLimit){
        config.set("max-entity-limit", maxBlockLimit);
    }

    /**
     * @return The default size of a block in a newly instanced miniature
     */
    public static double getDefaultSize(){
        return config.getDouble("default-size");
    }

    /**
     * Set the default size of a block in a newly instanced miniature
     *
     * @param size The new default size of a block in a newly instanced miniature
     */
    public static void setDefaultSize(double size){
        config.set("default-size", size);
    }

    public static Material getAdjusterToolMaterial(){
        return Material.matchMaterial(config.getString("adjuster-tool"));
    }

    public static void setAdjusterToolMaterial(Material material){
        config.set("adjuster-tool", material.name());
    }

}
