package de.leghast.miniaturise.instance.settings;

public class SizeSettings {

    private AdjusterSettings parent;

    private double factor = 1;

    public SizeSettings(AdjusterSettings parent){
        this.parent = parent;
    }

    public double getFactor(){
        return factor;
    }

    public void setFactor(double factor){
        this.factor = factor;
    }

}
