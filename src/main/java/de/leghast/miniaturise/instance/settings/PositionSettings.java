package de.leghast.miniaturise.instance.settings;

public class PositionSettings {

    private AdjusterSettings parent;

    private double customFactor = 0;

    public PositionSettings(AdjusterSettings parent){
        this.parent = parent;
    }

    public double getCustomFactor(){
        return customFactor;
    }

    public void setCustomFactor(double factor){
        this.customFactor = factor;
    }

}
