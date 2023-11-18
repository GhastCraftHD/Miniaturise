package de.leghast.miniaturise.manager;


import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.region.Region;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class RegionManager {

    private Miniaturise main;
    private Location loc1 = null;
    private Location loc2 = null;
    private Region region = null;

    public RegionManager(Miniaturise main){
        this.main = main;
    }

    public void setLoc1(Location location, Player player){
        loc1 = location;
        player.sendMessage("§aFirst position was set to " +
                (int) loc1.getX() + ", " +
                (int) loc1.getY() + ", " +
                (int) loc1.getZ());
    }

    public void setLoc2(Location location, Player player){
        loc2 = location;
        player.sendMessage("§aSecond position was set to " +
                (int) loc2.getX() + ", " +
                (int) loc2.getY() + ", " +
                (int) loc2.getZ());
    }

    public Location getLoc1(){
        return loc1;
    }

    public Location getLoc2(){
        return loc2;
    }

    public void createRegion(Player player){
        if(loc1 != null && loc2 != null){
            try {
                region = new Region(loc1, loc2);
                player.sendMessage("§aThe region was selected");
            }catch (IllegalArgumentException e){
                player.sendMessage("§cLocations must be on the same world");
            }
        }else{
            player.sendMessage("§cYou have not specified two positions");
        }
    }

    public void placeMiniature(Player player){
        if(region == null) {
           player.sendMessage("§cPlease select a region first");
        }else{
            main.getMiniatureManager().pasteMiniature(player);
        }
    }

    public void cutSelection(){
        for(Block block : region.getBlocks()){
            block.setType(Material.AIR);
        }
    }

    public Region getRegion(){
        return region;
    }

}
