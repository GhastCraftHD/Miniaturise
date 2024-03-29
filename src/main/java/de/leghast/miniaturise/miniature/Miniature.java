package de.leghast.miniaturise.miniature;

import de.leghast.miniaturise.region.Region;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.VoxelShape;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a miniature version of a region or placed miniature.
 * The miniature is a collection of blocks with a specified size.
 */
public class Miniature implements Serializable {

    // List of blocks that make up the miniature
    @Serial
    private static final long serialVersionUID = 1;

    private List<MiniatureBlock> blocks;

    // The size of the blocks in the miniature
    private double size;
    private ItemStack thumbnail;

    /**
     * Constructs a new Miniature from a region.
     *
     * @param region The region to create the miniature from.
     * @param origin The origin location of the miniature.
     * @param size The size of the blocks in the miniature.
     */
    public Miniature(Region region, Location origin, double size){
        blocks = new ArrayList<>();
        // Iterate over each block in the region
        for(Block block : region.getBlocks()){
            // If the block is not air, and it borders air or a block that is not full
            if(!block.getType().isAir() && (bordersAir(block) || bordersNotFullBlock(block))) {
                // Create a new miniature block with the relative position and block data
                MiniatureBlock mb;
                mb = new MiniatureBlock(
                        block.getX() - (int) origin.getX(),
                        block.getY() - (int) origin.getY(),
                        block.getZ() - (int) origin.getZ(),
                        block.getBlockData(),
                        size);
                // Add the miniature block to the list of blocks
                blocks.add(mb);
            }
        }
        // Set the size of the blocks in the miniature
        this.size = size;
    }

    /**
     * Constructs a new Miniature from a placed miniature.
     *
     * @param placedMiniature The placed miniature to create the miniature from.
     * @param origin The origin location of the miniature.
     * @param size The size of the blocks in the miniature.
     */
    public Miniature(PlacedMiniature placedMiniature, Location origin, double size){
        blocks = new ArrayList<>();
        // If the placed miniature is not empty
        if(placedMiniature.getBlockDisplays().isEmpty()) blocks = new ArrayList<>();

        // Iterate over each block display in the placed miniature
        for(BlockDisplay bd : placedMiniature.getBlockDisplays()){
            // Create a new miniature block with the relative position and block data
            MiniatureBlock mb;
            mb = new MiniatureBlock(
                    bd.getX() - origin.getX(),
                    bd.getY() - origin.getY(),
                    bd.getZ() - origin.getZ(),
                    bd.getBlock(),
                    size);
            // Add the miniature block to the list of blocks
            blocks.add(mb);
        }
        // Set the size of the blocks in the miniature
        this.size = size;

    }

    /**
     * Scales up the size of the blocks in the miniature by a specified factor.
     *
     * @param scale The factor by which to scale up the blocks.
     */
    public void scaleUp(double scale){
        // Iterate over each block in the miniature
        for(MiniatureBlock mb : blocks){
            // Scale up the x, y, and z coordinates of the block by the specified factor
            mb.setX(mb.getX() * scale);
            mb.setY(mb.getY() * scale);
            mb.setZ(mb.getZ() * scale);
            // Scale up the size of the block by the specified factor
            mb.setSize(mb.getSize() * scale);
        }
        // Scale up the size of the blocks in the miniature by the specified factor
        size *= scale;
    }

    /**
     * Scales down the size of the blocks in the miniature by a specified factor.
     *
     * @param scale The factor by which to scale down the blocks.
     */
    public void scaleDown(double scale){
        // Iterate over each block in the miniature
        for(MiniatureBlock mb : blocks){
            // Scale down the x, y, and z coordinates of the block by the specified factor
            mb.setX(mb.getX() / scale);
            mb.setY(mb.getY() / scale);
            mb.setZ(mb.getZ() / scale);
            // Scale down the size of the block by the specified factor
            mb.setSize(mb.getSize() / scale);
        }
        // Scale down the size of the blocks in the miniature by the specified factor
        size /= scale;
    }

    /**
     * Returns the size of the blocks in the miniature.
     *
     * @return The size of the blocks.
     */
    public double getSize(){
        return size;
    }

    /**
     * Returns a list of all blocks in the miniature.
     *
     * @return The list of blocks.
     */
    public List<MiniatureBlock> getBlocks(){
        return blocks;
    }

    /**
     * Returns the number of blocks in the miniature.
     *
     * @return The number of blocks.
     */
    public int getBlockCount(){
        return blocks.size();
    }

    /**
     * Checks if a block borders air.
     *
     * @param block The block to check.
     * @return True if the block borders air, false otherwise.
     */
    private boolean bordersAir(Block block) {
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                for (int dz = -1; dz <= 1; dz += 2) {
                    if (block.getRelative(dx, dy, dz).getType().isAir()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a block borders a block that is not full.
     *
     * @param block The block to check.
     * @return True if the block borders a block that is not full, false otherwise.
     */
    private boolean bordersNotFullBlock(Block block) {
        // Check if any of the six blocks surrounding the given block is not full
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dy == 0 && dz == 0) continue; // Skip the block itself
                    if (isNotFullBlock(block.getRelative(dx, dy, dz))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a block is not full.
     *
     * @param block The block to check.
     * @return True if the block is not full, false otherwise.
     */
    private boolean isNotFullBlock(Block block){
        // Get the collision shape of the block
        VoxelShape collisionShape = block.getCollisionShape();

        double volume = 0;
        // Calculate the total volume of the bounding boxes in the collision shape
        for(BoundingBox bb : collisionShape.getBoundingBoxes()){
            volume += bb.getVolume();
        }
        // If the total volume is not 1, the block is not full
        return volume != 1;
    }

    /**
     * Generates a thumbnail for the miniature.
     * This method generates a thumbnail for the miniature by finding the most common material in the blocks of the miniature.
     * It creates a frequency map of the materials, and then sets the thumbnail to be an ItemStack of the most common material.
     */
    public void generateThumbnail(){
        Map<Material, Integer> materialFrequencyMap = new HashMap<>();

        // Iterate over each block in the miniature
        for (MiniatureBlock mb : blocks) {
            // Get the block data and material of the block
            BlockData blockData = mb.getBlockData();
            Material material = blockData.getMaterial();
            // Increment the frequency of the material in the map
            materialFrequencyMap.put(material, materialFrequencyMap.getOrDefault(material, 0) + 1);
        }

        // Set the thumbnail to be an ItemStack of the most common material
        this.thumbnail = new ItemStack(Collections.max(materialFrequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey());
    }

    /**
     * Returns the thumbnail of the miniature.
     * This method returns the ItemStack that is being used as the thumbnail for the miniature.
     *
     * @return The ItemStack used as the thumbnail.
     */
    public ItemStack getThumbnail(){
        return thumbnail;
    }
}