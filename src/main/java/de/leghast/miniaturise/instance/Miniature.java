package de.leghast.miniaturise.instance;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class Miniature {

    private Region region;

    private List<MiniatureBlock> blocks;

    private double size;

    public Miniature(@NotNull Region region, Location origin, double size){
        this.region = region;

        blocks = new ArrayList<>();
        for(Block block : region.getBlocks()){
            if(block.getType() != Material.AIR && bordersAir(block) && bordersSolid(block)) {
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
    }

    public void scaleMiniature(double scale){
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

    private boolean bordersSolid(Block block) {
        if (block.getRelative(0, 1, 0).getType().isTransparent()) {
            return true;
        }
        if (block.getRelative(0, -1, 0).getType().isTransparent()) {
            return true;
        }
        if (block.getRelative(1, 0, 0).getType().isTransparent() ||
                block.getRelative(-1, 0, 0).getType().isTransparent() ||
                block.getRelative(0, 0, 1).getType().isTransparent() ||
                block.getRelative(0, 0, -1).getType().isTransparent()) {
            return true;
        }
        return false;
    }

}
