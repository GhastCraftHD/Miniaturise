package de.leghast.miniaturise.instance.miniature;

import de.leghast.miniaturise.instance.region.Region;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class Miniature {

    private List<MiniatureBlock> blocks;

    private double size;

    public Miniature(Region region, Location origin, double size){
        blocks = new ArrayList<>();
        for(Block block : region.getBlocks()){
            if(!block.getType().isAir() && (bordersAir(block) || bordersNotFullBlock(block))) {
                MiniatureBlock mb;
                mb = new MiniatureBlock(
                        block.getX() - (int) origin.getX(),
                        block.getY() - (int) origin.getY(),
                        block.getZ() - (int) origin.getZ(),
                        block.getBlockData(),
                        size);
                blocks.add(mb);
            }
        }
        this.size = size;
    }

    public Miniature(PlacedMiniature placedMiniature, Location origin, double size){
        blocks = new ArrayList<>();
        if(!placedMiniature.getBlockDisplays().isEmpty()){
            for(BlockDisplay bd : placedMiniature.getBlockDisplays()){
                MiniatureBlock mb;
                mb = new MiniatureBlock(
                        bd.getX() - origin.getX(),
                        bd.getY() - origin.getY(),
                        bd.getZ() - origin.getZ(),
                        bd.getBlock(),
                        size);
                blocks.add(mb);
            }
            this.size = size;
        }else{
            blocks = null;
        }
    }

    public void scale(double scale){
        for(MiniatureBlock mb : blocks){
            mb.setX(mb.getX() * scale);
            mb.setY(mb.getY() * scale);
            mb.setZ(mb.getZ() * scale);
            mb.setSize(mb.getSize() * scale);
        }
        size *= scale;
    }

    public double getSize(){
        return size;
    }

    public List<MiniatureBlock> getBlocks(){
        return blocks;
    }

    private boolean bordersAir(Block block) {
        return block.getRelative(0, 1, 0).getType().isAir() ||
                block.getRelative(0, -1, 0).getType().isAir() ||
                block.getRelative(1, 0, 0).getType().isAir() ||
                block.getRelative(-1, 0, 0).getType().isAir() ||
                block.getRelative(0, 0, 1).getType().isAir() ||
                block.getRelative(0, 0, -1).getType().isAir();
    }
    private boolean bordersNotFullBlock(Block block) {
        return isNotFullBlock(block.getRelative(0, 1, 0)) ||
                isNotFullBlock(block.getRelative(0, -1, 0)) ||
                isNotFullBlock(block.getRelative(1, 0, 0)) ||
                isNotFullBlock(block.getRelative(-1, 0, 0)) ||
                isNotFullBlock(block.getRelative(0, 0, 1)) ||
                isNotFullBlock(block.getRelative(0, 0, -1));
    }

    private boolean isNotFullBlock(Block block){
        VoxelShape collisionShape = block.getCollisionShape();
        if(collisionShape != null){
            double volume = 0;
            for(BoundingBox bb : collisionShape.getBoundingBoxes()){
                volume += bb.getVolume();
            }
            return volume != 1;
        }
        return true;
    }

}