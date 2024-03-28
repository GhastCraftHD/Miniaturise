package de.leghast.miniaturise.settings;

import de.leghast.miniaturise.util.Util;

public class FactorSettings {

    private AdjusterSettings parent;

    private double factor;

    public FactorSettings(AdjusterSettings parent, double factor){
        this.parent = parent;
        this.factor = factor;
    }

    public double getFactor(){
        return factor;
    }

    public void setFactor(double factor){
        this.factor = factor;
    }

    public void setFactor(String factor){
        try{
            this.factor = Double.parseDouble(factor);
            parent.getPlayer().sendMessage(Util.PREFIX + "§aThe factor was set to §e" + this.factor + " block" + (this.factor == 1 ? "" : "s"));
        }catch(NumberFormatException e){
            parent.getPlayer().sendMessage(Util.PREFIX + "§cPlease provide a valid factor");
        }
    }


}
