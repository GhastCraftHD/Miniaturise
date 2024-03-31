package de.leghast.miniaturise.handler;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.settings.AdjusterSettings;
import de.leghast.miniaturise.settings.FactorSettings;
import de.leghast.miniaturise.ui.Page;
import de.leghast.miniaturise.ui.UserInterface;
import de.leghast.miniaturise.ui.AnvilInputHelper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SizeInteractionHandler {

    public SizeInteractionHandler(Miniaturise main, int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        FactorSettings sizeSettings = settings.getSizeSettings();

        switch (slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.ADJUSTER_TOOL));
            case 18 -> settings.setPage(Page.ROTATION);
            //case 27 -> settings.setPage(Page.SAVED);
            case 20 -> sizeSettings.setFactor(0.25);
            case 21 -> sizeSettings.setFactor(0.5);
            case 22 -> sizeSettings.setFactor(1);
            case 23 -> sizeSettings.setFactor(main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize());
            case 24 -> AnvilInputHelper.getCustomNumberInput(main, player, settings.getPage(), sizeSettings.getFactor());
            default -> new GeneralInterfaceHandler(main, slot, player);
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

}
