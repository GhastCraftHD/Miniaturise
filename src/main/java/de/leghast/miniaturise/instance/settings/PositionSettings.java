package de.leghast.miniaturise.instance.settings;

import de.leghast.miniaturise.ui.Axis;

public class PositionSettings {

    private AdjusterSettings parent;

    private double factor = 1;

    private Axis axis = Axis.X;

    public PositionSettings(AdjusterSettings parent){
        this.parent = parent;
    }

    public double getFactor(){
        return factor;
    }

    public void setFactor(double factor){
        this.factor = factor;
    }

    public Axis getAxis(){
        return axis;
    }

    public void setAxis(Axis axis){
        this.axis = axis;
    }

}
