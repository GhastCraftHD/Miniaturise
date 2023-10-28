package de.leghast.miniaturise.manager;


import de.leghast.miniaturise.region.Region;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class RegionManager {

    public static Location loc1 = null;
    public static Location loc2 = null;
    public static Region region = null;

    public static void setLoc1(Location location, Player player){
        loc1 = location;
        player.sendMessage("§aFirst position was set to " +
                (int) RegionManager.loc1.getX() + ", " +
                (int) RegionManager.loc1.getY() + ", " +
                (int) RegionManager.loc1.getZ());
    }

    public static void setLoc2(Location location, Player player){
        loc2 = location;
        player.sendMessage("§aSecond position was set to " +
                (int) RegionManager.loc2.getX() + ", " +
                (int) RegionManager.loc2.getY() + ", " +
                (int) RegionManager.loc2.getZ());
    }

    public static Location getLoc1(){
        return loc1;
    }

    public static Location getLoc2(){
        return loc2;
    }

    public static void createRegion(Player player){
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

    public static void placeMiniature(Player player){
        if(region == null) {
           player.sendMessage("§cPlease select a region first");
        }else{
            MiniatureManager.pasteMiniature(player);
        }
    }

    public static void cutSelection(){
        for(Block block : region.getBlocks()){
            block.setType(Material.AIR);
        }
    }

}
