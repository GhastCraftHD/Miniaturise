package de.leghast.miniaturise.miniature;

import org.bukkit.Material;

public class MiniatureBlock {

    public double x;
    public double y;
    public double z;
    public Material material;
    public double size;

    public MiniatureBlock(double x, double y, double z, Material material, double size){
        this.x = x;
        this.y = y;
        this.z = z;
        this.material = material;
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

    public Material getMaterial(){
        return material;
    }

    public double getSize(){
        return size;
    }

}
