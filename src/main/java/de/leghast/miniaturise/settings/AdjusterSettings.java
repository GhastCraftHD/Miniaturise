package de.leghast.miniaturise.settings;

import de.leghast.miniaturise.ui.Page;
import org.bukkit.entity.Player;

public class AdjusterSettings {

    private final Player player;

    private final DimensionSettings positionSettings;
    private final FactorSettings sizeSettings;
    private final DimensionSettings rotationSettings;

    private Page page;

    public AdjusterSettings(Player player){
        this.player = player;

        positionSettings = new DimensionSettings(this, 1);
        sizeSettings = new FactorSettings(this, 0.5);
        rotationSettings = new DimensionSettings(this, 45);

        page = Page.POSITION;
    }

    public DimensionSettings getPositionSettings(){
        return positionSettings;
    }

    public FactorSettings getSizeSettings(){
        return sizeSettings;
    }

    public DimensionSettings getRotationSettings(){
        return rotationSettings;
    }

    public Page getPage(){
        return page;
    }

    public void setPage(Page page){
        this.page = page;
    }

    public Player getPlayer(){
        return player;
    }

}
