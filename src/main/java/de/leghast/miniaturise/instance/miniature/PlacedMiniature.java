package de.leghast.miniaturise.instance.miniature;

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

    public void remove(){
        for(BlockDisplay bd : blockDisplays){
            bd.remove();
        }
    }

    public void scaleUp(double scale){
        Bukkit.getScheduler().runTaskAsynchronously(getMain(), () -> {
            Location origin = blockDisplays.get(0).getLocation();
            Miniature miniature = new Miniature(this, origin, blockSize);
            miniature.scaleUp(scale);
            rearrange(origin, miniature);
            blockSize *= scale;
        });
    }

    public void scaleDown(double scale){
        Bukkit.getScheduler().runTaskAsynchronously(getMain(), () -> {
            Location origin = blockDisplays.get(0).getLocation();
            Miniature miniature = new Miniature(this, origin, blockSize);
            miniature.scaleDown(scale);
            rearrange(origin, miniature);
            blockSize /= scale;
                });
    }

    private void rearrange(Location origin, Miniature miniature) {
        Bukkit.getScheduler().runTaskAsynchronously(getMain(), () -> {
            for(int i = 0; i < getBlockCount(); i++){
                BlockDisplay bd = blockDisplays.get(i);
                MiniatureBlock mb = miniature.getBlocks().get(i);
                Bukkit.getScheduler().scheduleSyncDelayedTask(getMain(), () -> {
                    bd.teleport(new Location( bd.getWorld(),
                            mb.getX() + origin.getX(),
                            mb.getY() + origin.getY(),
                            mb.getZ() + origin.getZ()));
                    Transformation transformation = bd.getTransformation();
                    transformation.getScale().set(mb.getSize());
                    bd.setTransformation(transformation);
                });
            }
        });
    }

    public void rotate(Axis axis, float angle){
        Location origin = blockDisplays.get(0).getLocation();
        float finalAngle = (float) Math.toRadians(angle);
        Bukkit.getScheduler().runTaskAsynchronously(getMain(), () -> {

            for(BlockDisplay bd : blockDisplays){

                Transformation transformation = bd.getTransformation();

                switch (axis){
                    case X -> transformation.getLeftRotation().rotateX(finalAngle);
                    case Y -> transformation.getLeftRotation().rotateY(finalAngle);
                    case Z -> transformation.getLeftRotation().rotateZ(finalAngle);
                }

                Vector3f newPositionVector = getRotatedPosition(
                        bd.getLocation().toVector().toVector3f(),
                        origin.toVector().toVector3f(),
                        axis,
                        finalAngle
                );

                Location newLocation = new Location(
                        bd.getLocation().getWorld(),
                        newPositionVector.x,
                        newPositionVector.y,
                        newPositionVector.z
                );
                Bukkit.getScheduler().scheduleSyncDelayedTask(getMain(), () -> {
                    bd.setTransformation(transformation);
                    bd.teleport(newLocation);
                });

            }
        });



    }

    private Vector3f getRotatedPosition(Vector3f pointToRotate, Vector3f origin, Axis axis, float angle){
        pointToRotate.sub(origin);
        Matrix3f rotationMatrix = new Matrix3f();

        switch (axis){
            case X -> rotationMatrix.rotationX(angle);
            case Y -> rotationMatrix.rotationY(angle);
            case Z -> rotationMatrix.rotationZ(angle);
        }

        rotationMatrix.transform(pointToRotate);

        pointToRotate.add(origin);

        return pointToRotate;
    }

    public void move(Vector addition){
        Bukkit.getScheduler().scheduleSyncDelayedTask(getMain(), () -> {
            for(BlockDisplay bd : blockDisplays){
                bd.teleport(bd.getLocation().add(addition));
            }
        });
    }

    public void move(Axis axis, double addition){
        switch (axis){
            case X -> move(new Vector(addition, 0, 0));
            case Y -> move(new Vector(0, addition, 0));
            case Z -> move(new Vector(0, 0, addition));
        }
    }

    public double getBlockSize() {
        return blockSize;
    }

    public int getBlockCount(){
        return blockDisplays.size();
    }

    public List<BlockDisplay> getBlockDisplays(){
        return blockDisplays;
    }

    private Miniaturise getMain(){
        return (Miniaturise) Bukkit.getPluginManager().getPlugin("Miniaturise");
    }

}
