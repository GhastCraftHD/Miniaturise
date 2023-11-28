package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.Miniature;
import de.leghast.miniaturise.instance.PlacedMiniature;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class manages the last selected miniatures of each online player
 * @author GhastCraftHD
 * */
public class MiniatureManager {

    private Miniaturise main;

    private HashMap<UUID, Miniature> miniatures;
    private HashMap<UUID, PlacedMiniature> placedMiniatures;

    public MiniatureManager(Miniaturise main){
        this.main = main;

        miniatures = new HashMap<>();
        placedMiniatures = new HashMap<>();
    }

    /**
     * @return The HashMap, that stores the last selected miniature by every player
     * */
    public HashMap<UUID, Miniature> getMiniatures(){
        return miniatures;
    }

    /**
     * @return The HashMap, that stores the last placed miniature by every player
     * */
    public HashMap<UUID, PlacedMiniature> getPlacedMiniatures(){
        return placedMiniatures;
    }

    /**
     * @return The miniature, the specified player has selected last
     *
     * @param uuid The UUID of the player, you want to get the last selected miniature from
     * */
    public Miniature getMiniature(UUID uuid){
        return miniatures.get(uuid);
    }

    /**
     * @return The placed miniature, the specified player has placed last
     *
     * @param uuid The UUID of the player, you want to get the last placed miniature from
     * */
    public PlacedMiniature getPlacedMiniature(UUID uuid){
        return placedMiniatures.get(uuid);
    }

    /**
     * @return Whether the specified player has a miniature selected
     * (if the specified player has an entry in the miniatures HashMap)
     *
     * @param uuid The UUID of the player, you want to check if they have selected a miniature
     */
    public boolean hasMiniature(UUID uuid){
        return miniatures.containsKey(uuid);
    }

    /**
     * @return Whether the specified player has placed a miniature
     * (if the specified player has an entry in the placedMiniatures HashMap)
     *
     * @param uuid The UUID of the player, you want to check if they have placed a miniature
     */
    public boolean hasPlacedMiniature(UUID uuid){
        return placedMiniatures.containsKey(uuid);
    }

    /**
     * Add a player that has just selected a miniature to the miniatures HashMap
     *
     * @param uuid The UUID of the player that has just selected a miniature
     * @param miniature The miniature the player has just selected
     */
    public void addMiniature(UUID uuid, Miniature miniature){
        miniatures.put(uuid, miniature);
    }

    /**
     * Add a player that has just placed a miniature to the miniatures HashMap
     *
     * @param uuid The UUID of the player that has just placed a miniature
     * @param placedMiniature The placed miniature the player has just placed
     */
    public void addPlacedMiniature(UUID uuid, PlacedMiniature placedMiniature){
        placedMiniatures.put(uuid, placedMiniature);
    }

    /**
     * Remove a players entry from the miniatures HashMap
     *
     * @param uuid The UUID of the player, you want to remove from the HashMap
     * */
    public void removeMiniature(UUID uuid){
        miniatures.remove(uuid);
    }

    /**
     * Remove a players entry from the placedMiniatures HashMap
     *
     * @param uuid The UUID of the player, you want to remove from the HashMap
     * */
    public void removePlacedMiniature(UUID uuid){
        placedMiniatures.remove(uuid);
    }

}
