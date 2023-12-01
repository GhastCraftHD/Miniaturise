package de.leghast.miniaturise.instance.region;

import org.bukkit.Location;

/**
 * This class is used to store the two locations a player can select and that are used to create a region
 * @author GhastCraftHD
 * */
public class SelectedLocations {

    private Location loc1;
    private Location loc2;

    public SelectedLocations(Location loc1, Location loc2){
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    /**
     * @return The first location, that was selected
     * */
    public Location getLoc1(){
        return loc1;
    }

    /**
     * @return The second location, that was selected
     * */
    public Location getLoc2(){
        return loc2;
    }

    /**
     * Sets the first location
     *
     * @param loc1 The location to replace the old selected location
     * */
    public void setLoc1(Location loc1){
        this.loc1 = loc1;
    }

    /**
     * Sets the second location
     *
     * @param loc2 The location to replace the old selected location
     * */
    public void setLoc2(Location loc2){
        this.loc2 = loc2;
    }

    /**
     * @return If both locations are set
     */
    public boolean isValid(){
        return loc1 != null && loc2 != null;
    }

}
