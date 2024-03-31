package de.leghast.miniaturise.handler;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.settings.AdjusterSettings;
import de.leghast.miniaturise.ui.Page;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GeneralInterfaceHandler {

    public GeneralInterfaceHandler(Miniaturise main, int slot, Player player) {
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.ADJUSTER_TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            //case 27 -> settings.setPage(Page.SAVED);
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


    }
}
