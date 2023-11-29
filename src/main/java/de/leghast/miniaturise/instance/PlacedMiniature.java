package de.leghast.miniaturise.instance;

import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class PlacedMiniature {

    private List<BlockDisplay> blockDisplays;
    private double blockSize;

    public PlacedMiniature(List<MiniatureBlock> blocks, Location origin) throws InvalidParameterException {
        if(!blocks.isEmpty()){
            blockDisplays = new ArrayList<>();
            blockSize = blocks.get(0).getSize();

            for(MiniatureBlock mb : blocks) {
                BlockDisplay bd;
                bd = (BlockDisplay) origin.getWorld().spawnEntity(new Location(
                                origin.getWorld(),
                                mb.getX() + ceil(origin.getX()),
                                mb.getY() + ceil(origin.getY()),
                                mb.getZ() + ceil(origin.getZ())),
                        EntityType.BLOCK_DISPLAY);
                bd.setBlock(mb.getBlockData());
                Transformation transformation = bd.getTransformation();
                transformation.getScale().set(mb.getSize());
                bd.setTransformation(transformation);
                blockDisplays.add(bd);
            }
        }else{
            throw new InvalidParameterException("The miniature block list is empty");
        }
    }

    public PlacedMiniature(List<BlockDisplay> blockDisplays) throws InvalidParameterException{
        this.blockDisplays = blockDisplays;
        if(!blockDisplays.isEmpty()){
            blockSize = blockDisplays.get(0).getTransformation().getScale().x;
        }else{
            throw new InvalidParameterException("The block display list is empty");
        }
    }

    public void removePlacedMiniature(){
        for(BlockDisplay bd : blockDisplays){
            bd.remove();
        }
    }

    public void scalePlacedMiniature(double scale){
        Location origin = blockDisplays.get(0).getLocation();
        Miniature miniature = new Miniature(this, origin, blockSize);
        miniature.scaleMiniature(scale);
        for(int i = 0; i < getBlockCount(); i++){
            BlockDisplay bd = blockDisplays.get(i);
            MiniatureBlock mb = miniature.getBlocks().get(i);
            bd.teleport(new Location( bd.getWorld(),
                    mb.getX() + origin.getX(),
                    mb.getY() + origin.getY(),
                    mb.getZ() + origin.getZ()));
            Transformation transformation = bd.getTransformation();
            transformation.getScale().set(mb.getSize());
            bd.setTransformation(transformation);
        }
        blockSize *= scale;
    }

    public void movePlacedMiniature(Vector addition){
        for(BlockDisplay bd : blockDisplays){
            bd.teleport(bd.getLocation().add(addition));
        }
    }

    public int getBlockCount(){
        return blockDisplays.size();
    }

    public List<BlockDisplay> getBlockDisplays(){
        return blockDisplays;
    }

}
