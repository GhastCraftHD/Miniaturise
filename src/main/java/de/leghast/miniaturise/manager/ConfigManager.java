package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(Miniaturise miniaturise){
        ConfigManager.config = miniaturise.getConfig();
        miniaturise.saveDefaultConfig();
    }

    public static Material getToolMaterial(){
        return Material.matchMaterial(config.getString("tool"));
    }

    public static void setToolMaterial(Material material){
        config.set("tool", material.toString());
    }


}
