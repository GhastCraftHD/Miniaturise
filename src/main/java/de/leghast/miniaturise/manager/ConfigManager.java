package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import io.papermc.paper.configuration.constraint.Constraints;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * This class manages the config settings for the Miniaturise plugin
 * @author GhastCraftHD
 * */
public class ConfigManager {

    private static FileConfiguration config;

    public static boolean CHECK_FOR_UPDATE;
    public static int MAX_ENTITY_LIMIT;
    public static double DEFAULT_SIZE;
    public static Material SELECTOR_TOOL;
    public static Material ADJUSTER_TOOL;

    public static void setUpConfig(Miniaturise main){
        ConfigManager.config = main.getConfig();
        CHECK_FOR_UPDATE = getBool("check-for-update");
        MAX_ENTITY_LIMIT = getInt("max-entity-limit");
        DEFAULT_SIZE = getDouble("default-size");
        SELECTOR_TOOL = Material.matchMaterial(getString("selector-tool"));
        ADJUSTER_TOOL = Material.matchMaterial(getString("adjuster-tool"));
    }

    private static String getString(String path){
        return config.getString(path);
    }

    private static boolean getBool(String path){
        return config.getBoolean(path);
    }

    private static int getInt(String path){
        return config.getInt(path);
    }

    private static double getDouble(String path){
        return config.getDouble(path);
    }

    private static Material getMaterial(String path){
        return Material.matchMaterial(getString(path));
    }

}
