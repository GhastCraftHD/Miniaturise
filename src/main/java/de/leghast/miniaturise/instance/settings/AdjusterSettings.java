package de.leghast.miniaturise.instance.settings;

import de.leghast.miniaturise.ui.Page;
import org.bukkit.entity.Player;

public class AdjusterSettings {

    private Player player;

    private PositionSettings positionSettings;
    private SizeSettings sizeSettings;
    private RotationSettings rotationSettings;

    private Page page;

    public AdjusterSettings(Player player){
        this.player = player;

        positionSettings = new PositionSettings(this);
        sizeSettings = new SizeSettings(this);
        rotationSettings = new RotationSettings(this);

        page = Page.POSITION;
    }

    public PositionSettings getPositionSettings(){
        return positionSettings;
    }

    public SizeSettings getSizeSettings(){
        return sizeSettings;
    }

    public RotationSettings getRotationSettings(){
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
