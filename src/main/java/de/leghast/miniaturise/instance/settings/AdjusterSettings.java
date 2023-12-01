package de.leghast.miniaturise.instance.settings;

import de.leghast.miniaturise.ui.Page;
import org.bukkit.entity.Player;

public class AdjusterSettings {

    private Player player;

    private PositionSettings positionSettings;
    private SizeSettings sizeSettings;

    private Page lastPage;

    public AdjusterSettings(Player player){
        this.player = player;

        positionSettings = new PositionSettings(this);
        sizeSettings = new SizeSettings(this);

        lastPage = Page.POSITION;
    }

    public PositionSettings getPositionSettings(){
        return positionSettings;
    }

    public SizeSettings getSizeSettings(){
        return sizeSettings;
    }

    public Page getLastPage(){
        return lastPage;
    }

    public void setLastPage(Page lastPage){
        this.lastPage = lastPage;
    }

}
