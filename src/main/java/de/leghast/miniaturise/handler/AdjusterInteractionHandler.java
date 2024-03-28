package de.leghast.miniaturise.handler;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.miniature.PlacedMiniature;
import de.leghast.miniaturise.settings.AdjusterSettings;
import de.leghast.miniaturise.ui.UserInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;

public class AdjusterInteractionHandler {

    public AdjusterInteractionHandler(Miniaturise main, Player player, Action action, EquipmentSlot hand){
        if(!main.getSettingsManager().hasAdjusterSettings(player.getUniqueId())){
            main.getSettingsManager().addAdjusterSettings(player.getUniqueId());
        }

        if(action.isLeftClick()){
            PlacedMiniature placedMiniature = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId());
            AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
            switch (main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage()){
                case POSITION -> placedMiniature.move(settings.getPositionSettings().getAxis(), -settings.getPositionSettings().getFactor());
                case SIZE -> placedMiniature.scaleDown(settings.getSizeSettings().getFactor());
                case ROTATION -> placedMiniature.rotate(settings.getRotationSettings().getAxis(), (float) -settings.getRotationSettings().getFactor());
            }

        }else if(action.isRightClick()){
            if(hand == EquipmentSlot.HAND){
                if(player.isSneaking()){
                    new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
                }else{
                    PlacedMiniature placedMiniature = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId());
                    AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                    switch (main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage()){
                        case POSITION -> placedMiniature.move(settings.getPositionSettings().getAxis(), settings.getPositionSettings().getFactor());
                        case SIZE -> placedMiniature.scaleUp(settings.getSizeSettings().getFactor());
                        case ROTATION -> placedMiniature.rotate(settings.getRotationSettings().getAxis(), (float) settings.getRotationSettings().getFactor());
                    }
                }
            }
        }
    }

}
