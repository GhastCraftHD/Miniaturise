package de.leghast.miniaturise.ui.page;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.ui.FrequentItems;
import de.leghast.miniaturise.ui.Page;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PositionPage {

    public static ItemStack[] getPositionPage(Miniaturise main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getAxis();

        FrequentItems.addPageSwitchItems(content, Page.POSITION);

        List<ItemStack> valueItems = FrequentItems.getValueItems(factor, main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize());

        int index = 11;
        for (ItemStack item : valueItems) {
            content[index++] = item;
        }

        FrequentItems.addAxisItems(content, axis);

        FrequentItems.addGeneralItems(content);

        return content;
    }

}
