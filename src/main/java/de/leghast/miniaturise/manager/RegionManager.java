package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.Region;
import de.leghast.miniaturise.instance.SelectedLocations;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class manages the selected locations and region of all online players
 * @author GhastCraftHD
 * */
public class RegionManager {

    private Miniaturise main;

    private HashMap<UUID, SelectedLocations> selectedLocations;
    private HashMap<UUID, Region> regions;

    public RegionManager(Miniaturise main){
        this.main = main;

        selectedLocations = new HashMap<>();
        regions = new HashMap<>();
    }

    /**
     * @return The HashMap, that stores the locations, each player has selected
     * */
    public HashMap<UUID, SelectedLocations> getSelectedLocations(){
        return selectedLocations;
    }

    /**
     * @return The HashMap, that stores the regions, each player has selected
     * */
    public HashMap<UUID, Region> getRegions(){
        return regions;
    }

    /**
     * @return The selected location from a player
     * NOTE: Returns null, if the specified player has not set any locations yet
     *
     * @param uuid The UUID of the player, you want to get the selected Locations from
     * */
    public SelectedLocations getSelectedLocations(UUID uuid){
        return selectedLocations.get(uuid);
    }

    /**
     * @return The selected region from a player
     * NOTE: Returns null, if the specified player has not created a region yet
     *
     * @param uuid The UUID of the player, you want to get the region from
     * */
    public Region getRegion(UUID uuid){
        return regions.get(uuid);
    }

    /**
     * @return Whether the specified player has selected locations
     * (if the specified player has an entry in the selectedLocations HashMap)
     *
     * @param uuid The UUID of the player, you want to check if they have selected locations
     * */
    public boolean hasSelectedLocations(UUID uuid){
        return selectedLocations.containsKey(uuid);
    }

    /**
     * @return Whether the specified player has a selected region
     * (if the specified player has an entry in the regions HashMap)
     *
     * @param uuid The UUID of the player, you want to check if they have selected a region
     */
    public boolean hasRegion(UUID uuid){
        return regions.containsKey(uuid);
    }

    /**
     * Add a player that has just selected a location to the selectedLocations HashMap
     *
     * @param uuid The UUID of the player, you want to store the selected locations from
     * @param locations The selected locations from the player
     * */
    public void addSelectedLocations(UUID uuid, SelectedLocations locations){
        selectedLocations.put(uuid, locations);
    }

    /**
     * Add a player that has just selected a region to the regions HashMap
     *
     * @param uuid The UUID of the player, you want to store the selected region from
     * @param region The selected region from the player
     * */
    public void addRegion(UUID uuid, Region region){
        regions.put(uuid, region);
    }

    /**
     * Remove a players entry from the selectedLocations HashMap
     *
     * @param uuid The UUID of the player, you want to remove from the HashMap
     * */
    public void removeSelectedLocations(UUID uuid){
        selectedLocations.remove(uuid);
    }

    /**
     * Remove a players entry from the regions HashMap
     *
     * @param uuid The UUID of the player, you want to remove from the HashMap
     * */
    public void removeRegion(UUID uuid){
        regions.remove(uuid);
    }

    public void removeClipboard(UUID uuid){
        removeSelectedLocations(uuid);
        removeRegion(uuid);
    }

}
