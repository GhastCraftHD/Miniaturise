package de.leghast.miniaturise.miniature;

import org.bukkit.block.data.BlockData;

public class MiniatureBlock {

    private double x;
    private double y;
    private double z;
    private BlockData blockData;
    private double size;

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

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }
    public void setZ(double z){
        this.z = z;
    }

    public void setBlockData(BlockData blockData){
        this.blockData = blockData;
    }

    public void setSize(double size){
        this.size = size;
    }

}
