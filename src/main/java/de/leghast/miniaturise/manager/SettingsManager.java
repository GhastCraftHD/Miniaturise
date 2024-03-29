package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.settings.AdjusterSettings;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class SettingsManager {

    private final HashMap<UUID, AdjusterSettings> adjusterSettings;

    public SettingsManager(){
        adjusterSettings = new HashMap<>();
    }

    public boolean hasAdjusterSettings(UUID uuid){
        return adjusterSettings.containsKey(uuid);
    }

    public AdjusterSettings getAdjusterSettings(UUID uuid){
        return adjusterSettings.get(uuid);
    }

    public void addAdjusterSettings(UUID uuid){
        adjusterSettings.put(uuid, new AdjusterSettings(Bukkit.getPlayer(uuid)));
    }

    public void removeAdjusterSettings(UUID uuid){
        adjusterSettings.remove(uuid);
    }

}
