package de.leghast.miniaturise.instance.settings;

public class SizeSettings {

    private AdjusterSettings parent;

    private double customFactor = 0;

    public SizeSettings(AdjusterSettings parent){
        this.parent = parent;
    }

    public double getCustomFactor(){
        return customFactor;
    }

    public void setCustomFactor(double factor){
        this.customFactor = factor;
    }

}
