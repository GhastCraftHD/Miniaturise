package de.leghast.miniaturise.instance.settings;

import de.leghast.miniaturise.ui.Interface;
import org.bukkit.entity.Player;

public class AdjusterSettings {

    private Player player;

    private PositionSettings positionSettings;
    private SizeSettings sizeSettings;

    private Interface lastSelectedInterface;

    public AdjusterSettings(Player player){
        this.player = player;

        positionSettings = new PositionSettings(this);
        sizeSettings = new SizeSettings(this);

        lastSelectedInterface = Interface.POSITION;
    }

    public PositionSettings getPositionSettings(){
        return positionSettings;
    }

    public SizeSettings getSizeSettings(){
        return sizeSettings;
    }

}
