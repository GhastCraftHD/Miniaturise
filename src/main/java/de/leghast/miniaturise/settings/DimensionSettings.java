package de.leghast.miniaturise.settings;

import org.bukkit.Axis;

public class DimensionSettings extends FactorSettings{

    private Axis axis = Axis.X;

    public DimensionSettings(AdjusterSettings parent, double factor){
        super(parent, factor);
    }

    public Axis getAxis(){
        return this.axis;
    }

    public void setAxis(Axis axis){
        this.axis = axis;
    }
}
