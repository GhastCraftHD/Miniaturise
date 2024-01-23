package de.leghast.miniaturise.instance.miniature;

import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class caches all necessary information for a block from a miniature that has not been spawned in yet
 * @author GhastCraftHD
 */
public class MiniatureBlock implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    private double x;
    private double y;
    private double z;
    private String blockData;
    private double size;

    /**
     * Constructs a miniature block
     * @param x The x coordinate relative to the origin
     * @param y The y coordinate relative to the origin
     * @param z The z coordinate relative to the origin
     * @param blockData The block data from the represented block
     * @param size The size(in blocks) of the miniature block
     * */
    public MiniatureBlock(double x, double y, double z, BlockData blockData, double size){
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockData = blockData.getAsString();
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
        return Bukkit.createBlockData(blockData);
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

    public void setSize(double size){
        this.size = size;
    }

}
