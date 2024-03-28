package de.leghast.miniaturise.miniature;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.Axis;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Matrix3f;
import org.joml.Vector3f;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class PlacedMiniature {

    private final List<BlockDisplay> blockDisplays;
    private double blockSize;

    public PlacedMiniature(List<MiniatureBlock> blocks, Location origin) throws InvalidParameterException {
        if (!blocks.isEmpty()) {
            blockDisplays = new ArrayList<>();
            blockSize = blocks.get(0).getSize();

            for (MiniatureBlock mb : blocks) {
                Bukkit.getScheduler().runTask(Miniaturise.getPlugin(Miniaturise.class), () -> {
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
                });
            }
        } else {
            throw new InvalidParameterException("The miniature block list is empty");
        }
    }

    public PlacedMiniature(List<BlockDisplay> blockDisplays) throws InvalidParameterException {
        this.blockDisplays = blockDisplays;
        if (!blockDisplays.isEmpty()) {
            Bukkit.getScheduler().runTask(Miniaturise.getPlugin(Miniaturise.class), () -> {
                blockSize = blockDisplays.get(0).getTransformation().getScale().x;
            });
        } else {
            throw new InvalidParameterException("The block display list is empty");
        }
    }

    public void remove() {
        Bukkit.getScheduler().runTask(Miniaturise.getPlugin(Miniaturise.class), () -> {
            for (BlockDisplay bd : blockDisplays) {
                bd.remove();
            }
        });
    }

    public void scaleUp(double scale) {
        Bukkit.getScheduler().runTask(Miniaturise.getPlugin(Miniaturise.class), () -> {
            Location origin = blockDisplays.get(0).getLocation();
            Miniature miniature = new Miniature(this, origin, blockSize);
            miniature.scaleUp(scale);
            rearrange(origin, miniature);
        });
    }

    public void scaleDown(double scale) {
        Bukkit.getScheduler().runTask(Miniaturise.getPlugin(Miniaturise.class), () -> {
            Location origin = blockDisplays.get(0).getLocation();
            Miniature miniature = new Miniature(this, origin, blockSize);
            miniature.scaleDown(scale);
            rearrange(origin, miniature);
            blockSize /= scale;
        });
    }

    private void rearrange(Location origin, Miniature miniature) {
        Bukkit.getScheduler().runTask(Miniaturise.getPlugin(Miniaturise.class), () -> {
            for (int i = 0; i < getBlockCount(); i++) {
                BlockDisplay bd = blockDisplays.get(i);
                MiniatureBlock mb = miniature.getBlocks().get(i);
                bd.teleport(new Location(bd.getWorld(),
                        mb.getX() + origin.getX(),
                        mb.getY() + origin.getY(),
                        mb.getZ() + origin.getZ()));
                Transformation transformation = bd.getTransformation();
                transformation.getScale().set(mb.getSize());
                bd.setTransformation(transformation);
            }
        });
    }

    /**
     * Rotates the miniature around a specified axis by a given angle.
     *
     * @param axis  The axis around which the miniature is to be rotated. This can be X, Y, or Z.
     * @param angle The angle by which the miniature is to be rotated, in degrees.
     */
    public void rotate(Axis axis, float angle) {
        // Get the location of the first block in the miniature as the origin of rotation
        Bukkit.getScheduler().runTask(Miniaturise.getPlugin(Miniaturise.class), () -> {
            Location origin = blockDisplays.get(0).getLocation();

            // Convert the angle from degrees to radians for the rotation
            float finalAngle = (float) Math.toRadians(angle);

            // Iterate over each block in the miniature
            for (BlockDisplay bd : blockDisplays) {

                // Get the current transformation of the block
                Transformation transformation = bd.getTransformation();

                // Rotate the block around the specified axis by the given angle
                switch (axis) {
                    case X -> transformation.getLeftRotation().rotateX(finalAngle);
                    case Y -> transformation.getLeftRotation().rotateY(finalAngle);
                    case Z -> transformation.getLeftRotation().rotateZ(finalAngle);
                }

                // Calculate the new position of the block after rotation
                Vector3f newPositionVector = getRotatedPosition(
                        bd.getLocation().toVector().toVector3f(),
                        origin.toVector().toVector3f(),
                        axis,
                        finalAngle
                );

                // Create a new location for the block with the calculated position
                Location newLocation = new Location(
                        bd.getLocation().getWorld(),
                        newPositionVector.x,
                        newPositionVector.y,
                        newPositionVector.z
                );

                // Update the block's transformation and teleport it to the new location
                bd.setTransformation(transformation);
                bd.teleport(newLocation);
            }
        });
    }

    /**
     * Calculates the new position of a point after rotation around a specified axis by a given angle.
     *
     * @param rotationOrigin The point to be rotated around.
     * @param origin         The origin of rotation.
     * @param axis           The axis around which the point is to be rotated. This can be X, Y, or Z.
     * @param angle          The angle by which the point is to be rotated, in radians.
     * @return The new position of the point after rotation.
     */
    private Vector3f getRotatedPosition(Vector3f rotationOrigin, Vector3f origin, Axis axis, float angle) {
        rotationOrigin.sub(origin);
        Matrix3f rotationMatrix = new Matrix3f();

        switch (axis) {
            case X -> rotationMatrix.rotationX(angle);
            case Y -> rotationMatrix.rotationY(angle);
            case Z -> rotationMatrix.rotationZ(angle);
        }

        rotationMatrix.transform(rotationOrigin);

        rotationOrigin.add(origin);

        return rotationOrigin;
    }

    /**
     * Moves all blocks in the miniature by a specified vector.
     *
     * @param addition The vector by which the blocks are to be moved.
     */
    public void move(Vector addition) {
        for (BlockDisplay bd : blockDisplays) {
            bd.teleport(bd.getLocation().add(addition));
        }
    }

    /**
     * Moves all blocks in the miniature along a specified axis by a given distance.
     *
     * @param axis     The axis along which the blocks are to be moved. This can be X, Y, or Z.
     * @param addition The distance by which the blocks are to be moved.
     */
    public void move(Axis axis, double addition) {
        switch (axis) {
            case X -> move(new Vector(addition, 0, 0));
            case Y -> move(new Vector(0, addition, 0));
            case Z -> move(new Vector(0, 0, addition));
        }
    }

    /**
     * Returns the size of the blocks in the miniature.
     *
     * @return The size of the blocks.
     */
    public double getBlockSize() {
        return blockSize;
    }

    /**
     * Returns the number of blocks in the miniature.
     *
     * @return The number of blocks.
     */
    public int getBlockCount() {
        return blockDisplays.size();
    }

    /**
     * Returns a list of all blocks in the miniature.
     *
     * @return The list of blocks.
     */
    public List<BlockDisplay> getBlockDisplays() {
        return blockDisplays;
    }

}
