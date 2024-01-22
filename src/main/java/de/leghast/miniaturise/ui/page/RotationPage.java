package de.leghast.miniaturise.ui.page;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.ui.Page;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RotationPage {

    public static ItemStack[] getRotationPage(Miniaturise main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getRotationSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getRotationSettings().getAxis();

        PageUtil.addPageSwitchItems(content, Page.ROTATION);

        ItemStack quarter = new ItemStack(Material.COAL);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.setDisplayName("§722.5 degrees");
        quarter.setItemMeta(quarterMeta);
        if(factor == 22.5){
            PageUtil.addGlint(quarter);
        }
        content[11] = quarter;

        ItemStack half = new ItemStack(Material.IRON_INGOT);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.setDisplayName("§745 degrees");
        half.setItemMeta(halfMeta);
        if(factor == 45){
            PageUtil.addGlint(half);
        }
        content[12] = half;

        ItemStack full = new ItemStack(Material.DIAMOND);
        ItemMeta fullMeta = full.getItemMeta();
        fullMeta.setDisplayName("§790 degrees");
        full.setItemMeta(fullMeta);
        if(factor == 90){
            PageUtil.addGlint(full);
        }
        content[13] = full;

        ItemStack miniatureBlockSize = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta miniatureBlockSizeMeta = miniatureBlockSize.getItemMeta();
        miniatureBlockSizeMeta.setDisplayName("§7180 degrees");
        miniatureBlockSize.setItemMeta(miniatureBlockSizeMeta);
        if(factor == 180){
            PageUtil.addGlint(miniatureBlockSize);
        }
        content[14] = miniatureBlockSize;

        ItemStack custom = new ItemStack(Material.PAPER);
        ItemMeta customMeta = custom.getItemMeta();
        customMeta.setDisplayName("§7Custom factor §e(" + factor + " degree" + (factor == 1 ? "" : "s") + ")");
        custom.setItemMeta(customMeta);
        boolean condition = factor != 22.5 && factor != 45 && factor != 90 && factor != 180;
        if(condition){
            PageUtil.addGlint(custom);
        }
        content[15] = custom;

        PageUtil.addAxisItems(content, axis);

        PageUtil.addGeneralItems(content);

        return content;
    }

}
