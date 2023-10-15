package de.leghast.miniaturise.miniature;

import de.leghast.miniaturise.region.RegionManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Transformation;

import java.util.ArrayList;
import java.util.List;

public class MiniatureManager {
    public static List<MiniatureBlock> miniature = new ArrayList<>();
    public static List<BlockDisplay> placedMiniature = new ArrayList<>();
    public static int default_size = 1;

    public static void miniaturiseSelection(Location origin){
        miniature.clear();
        for(Block block : RegionManager.region.getBlocks()){
            MiniatureBlock mb;
            mb = new MiniatureBlock(block.getX() - (int) origin.getX(),
                    block.getY() - (int) origin.getY(),
                    block.getZ() - (int) origin.getZ(),
                    block.getType(),
                    default_size);
            miniature.add(mb);
        }
    }

    public static void pasteMiniature(Player player){
        placedMiniature.clear();
        for(MiniatureBlock mb : miniature){
            BlockDisplay bd;
            bd = (BlockDisplay) player.getWorld().spawnEntity(new Location(
                    player.getWorld(),
                    mb.getX() + (int) player.getLocation().getX(),
                    mb.getY() + (int) player.getLocation().getY(),
                    mb.getZ() + (int) player.getLocation().getZ()), EntityType.BLOCK_DISPLAY);
            bd.setBlock(mb.getMaterial().createBlockData());
            Transformation transformation = bd.getTransformation();
            transformation.getScale().set(mb.size);
            bd.setTransformation(transformation);
            placedMiniature.add(bd);
        }
    }

    public static void deleteMiniature(){
        for(BlockDisplay bd : placedMiniature){
            bd.remove();
        }
    }

    public static void scaleMiniature(double scale){
        if(!miniature.isEmpty()){
            for(MiniatureBlock mb : miniature){
                mb.x = mb.x * scale;
                mb.y = mb.y * scale;
                mb.z = mb.z * scale;
                mb.size = scale;
            }
        }
    }

}
