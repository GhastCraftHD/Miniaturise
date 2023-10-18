package de.leghast.miniaturise.miniature;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

public class MiniatureBlock {

    public double x;
    public double y;
    public double z;
    public BlockData blockData;
    public double size;

    public MiniatureBlock(double x, double y, double z, BlockData blockData, double size){
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockData = blockData;
        this.size = size;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }


    public BlockData getBlockData(){
        return blockData;
    }

    public double getSize(){
        return size;
    }

}
