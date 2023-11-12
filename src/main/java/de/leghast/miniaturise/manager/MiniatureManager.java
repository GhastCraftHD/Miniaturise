package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.miniature.MiniatureBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Transformation;

import java.util.ArrayList;
import java.util.List;

public class MiniatureManager {
    public static List<MiniatureBlock> miniature = new ArrayList<>();
    public static List<MiniatureBlock> scaledMiniature = new ArrayList<>();
    public static List<BlockDisplay> placedMiniature = new ArrayList<>();
    public static List<BlockDisplay>[] placedMiniatures;
    public static int default_size = 1;

    public static void miniaturiseSelection(Location origin){
        miniature.clear();
        for(Block block : RegionManager.region.getBlocks()){
            MiniatureBlock mb;
                mb = new MiniatureBlock(
                        block.getX() - (int) origin.getX(),
                        block.getY() - (int) origin.getY(),
                        block.getZ() - (int) origin.getZ(),
                        block.getBlockData(),
                        default_size);
                        miniature.add(mb);
        }
    }

    public static boolean bordersAir(Block block) {
        if (block.getRelative(0, 1, 0).getType() == Material.AIR) {
            return true;
        }
        if (block.getRelative(0, -1, 0).getType() == Material.AIR) {
            return true;
        }
        if (block.getRelative(1, 0, 0).getType() == Material.AIR ||
                block.getRelative(-1, 0, 0).getType() == Material.AIR ||
                block.getRelative(0, 0, 1).getType() == Material.AIR ||
                block.getRelative(0, 0, -1).getType() == Material.AIR) {
            return true;
        }
        return false;
    }

    public static boolean bordersSolid(Block block) {
        if (block.getRelative(0, 1, 0).isSolid()) {
            return true;
        }
        if (block.getRelative(0, -1, 0).isSolid()) {
            return true;
        }
        if (block.getRelative(1, 0, 0).isSolid() ||
                block.getRelative(-1, 0, 0).isSolid() ||
                block.getRelative(0, 0, 1).isSolid() ||
                block.getRelative(0, 0, -1).isSolid()) {
            return true;
        }
        return false;
    }

    public static void pasteMiniature(Player player){
        placedMiniature.clear();
        if(!miniature.isEmpty()) {
            for (MiniatureBlock mb : miniature) {
                spawnBlockDisplays(mb, player);
            }
        }else{
            player.sendMessage("§cPlease select a region first");
        }
    }

    public static void spawnBlockDisplays(MiniatureBlock mb, Player player){
        BlockDisplay bd;
        bd = (BlockDisplay) player.getWorld().spawnEntity(new Location(
                player.getWorld(),
                mb.getX() + (int) player.getLocation().getX(),
                mb.getY() + (int) player.getLocation().getY(),
                mb.getZ() + (int) player.getLocation().getZ()), EntityType.BLOCK_DISPLAY);
        bd.setBlock(mb.getBlockData());
        Transformation transformation = bd.getTransformation();
        transformation.getScale().set(mb.getSize());
        bd.setTransformation(transformation);
        placedMiniature.add(bd);
        if (bd.getBlock().equals(Material.AIR.createBlockData())) {
            bd.remove();
        }
    }

    public static void deleteMiniature(){
        for(BlockDisplay bd : placedMiniature){
            bd.remove();
        }
    }

    public static void scaleMiniature(double scale, Player player){
        if(!miniature.isEmpty()){
            for(MiniatureBlock mb : miniature){
                mb.setX(mb.getX() * scale);
                mb.setY(mb.getY() * scale);
                mb.setZ(mb.getZ() * scale);
                mb.setSize(mb.getSize() * scale);
            }
        }else{
            player.sendMessage("§cPlease select a region first");
        }
    }

}
