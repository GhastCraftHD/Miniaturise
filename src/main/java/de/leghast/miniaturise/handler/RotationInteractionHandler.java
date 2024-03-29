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

public class RotationInteractionHandler {

    public RotationInteractionHandler(Miniaturise main, int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings rotationSettings = settings.getRotationSettings();

        switch (slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.ADJUSTER_TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            //case 27 -> settings.setPage(Page.SAVED);
            case 11 -> rotationSettings.setFactor(22.5);
            case 12 -> rotationSettings.setFactor(45);
            case 13 -> rotationSettings.setFactor(90);
            case 14 -> rotationSettings.setFactor(180);
            case 15 -> AnvilInputHelper.getCustomNumberInput(main, player, settings.getPage(), rotationSettings.getFactor());
            case 30 -> rotationSettings.setAxis(Axis.X);
            case 31 -> rotationSettings.setAxis(Axis.Y);
            case 32 -> rotationSettings.setAxis(Axis.Z);
            case 26 -> {
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).remove();
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

}
