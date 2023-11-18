package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private Miniaturise main;
    private FileConfiguration config;

    public ConfigManager(Miniaturise main){
        this.main = main;
        config = main.getConfig();
        main.saveDefaultConfig();
    }

    public Material getToolMaterial(){
        return Material.matchMaterial(config.getString("tool"));
    }

    public void setToolMaterial(Material material){
        config.set("tool", material.toString());
    }


}
