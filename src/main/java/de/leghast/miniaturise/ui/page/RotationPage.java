package de.leghast.miniaturise.ui.page;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.ui.FrequentItems;
import de.leghast.miniaturise.ui.Page;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RotationPage {

    public static ItemStack[] getRotationPage(Miniaturise main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getRotationSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getRotationSettings().getAxis();

        FrequentItems.addPageSwitchItems(content, Page.ROTATION);

        ItemStack quarter = new ItemStack(Material.COAL);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.setDisplayName("§722.5 degrees");
        quarter.setItemMeta(quarterMeta);
        if(factor == 22.5){
            FrequentItems.addGlint(quarter);
        }
        content[11] = quarter;

        ItemStack half = new ItemStack(Material.IRON_INGOT);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.setDisplayName("§745 degrees");
        half.setItemMeta(halfMeta);
        if(factor == 45){
            FrequentItems.addGlint(half);
        }
        content[12] = half;

        ItemStack full = new ItemStack(Material.DIAMOND);
        ItemMeta fullMeta = full.getItemMeta();
        fullMeta.setDisplayName("§790 degrees");
        full.setItemMeta(fullMeta);
        if(factor == 90){
            FrequentItems.addGlint(full);
        }
        content[13] = full;

        ItemStack miniatureBlockSize = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta miniatureBlockSizeMeta = miniatureBlockSize.getItemMeta();
        miniatureBlockSizeMeta.setDisplayName("§7180 degrees");
        miniatureBlockSize.setItemMeta(miniatureBlockSizeMeta);
        if(factor == 180){
            FrequentItems.addGlint(miniatureBlockSize);
        }
        content[14] = miniatureBlockSize;

        ItemStack custom = new ItemStack(Material.PAPER);
        ItemMeta customMeta = custom.getItemMeta();
        customMeta.setDisplayName("§7Custom factor §e(" + factor + " degree" + (factor == 1 ? "" : "s") + ")");
        custom.setItemMeta(customMeta);
        boolean condition = factor != 22.5 && factor != 45 && factor != 90 && factor != 180;
        if(condition){
            FrequentItems.addGlint(custom);
        }
        content[15] = custom;

        FrequentItems.addAxisItems(content, axis);

        FrequentItems.addGeneralItems(content);

        return content;
    }

}
