package de.leghast.miniaturise.handler;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.settings.AdjusterSettings;
import de.leghast.miniaturise.settings.DimensionSettings;
import de.leghast.miniaturise.ui.Page;
import de.leghast.miniaturise.ui.UserInterface;
import de.leghast.miniaturise.ui.AnvilInputHelper;
import org.bukkit.Axis;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PositionInteractionHandler {

    public PositionInteractionHandler(Miniaturise main, int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings positionSettings = settings.getPositionSettings();

        switch (slot){
            case 11 -> positionSettings.setFactor(0.25);
            case 12 -> positionSettings.setFactor(0.5);
            case 13 -> positionSettings.setFactor(1);
            case 14 -> positionSettings.setFactor(main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize());
            case 15 -> AnvilInputHelper.getCustomNumberInput(main, player, settings.getPage(), positionSettings.getFactor());
            case 30 -> positionSettings.setAxis(Axis.X);
            case 31 -> positionSettings.setAxis(Axis.Y);
            case 32 -> positionSettings.setAxis(Axis.Z);
            default -> new GeneralInterfaceHandler(main, slot, player);
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

}
