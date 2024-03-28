package de.leghast.miniaturise.ui.page;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.ui.FrequentItems;
import de.leghast.miniaturise.ui.Page;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PositionPage {

    public static ItemStack[] getPositionPage(Miniaturise main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getAxis();

        FrequentItems.addPageSwitchItems(content, Page.POSITION);

        ItemStack quarter = new ItemStack(Material.COAL);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.setDisplayName("§70.25 blocks");
        quarter.setItemMeta(quarterMeta);
        if(factor == 0.25){
            FrequentItems.addGlint(quarter);
        }
        content[11] = quarter;

        ItemStack half = new ItemStack(Material.IRON_INGOT);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.setDisplayName("§70.5 blocks");
        half.setItemMeta(halfMeta);
        if(factor == 0.5){
            FrequentItems.addGlint(half);
        }
        content[12] = half;

        ItemStack full = new ItemStack(Material.DIAMOND);
        ItemMeta fullMeta = full.getItemMeta();
        fullMeta.setDisplayName("§71 block");
        full.setItemMeta(fullMeta);
        if(factor == 1){
            FrequentItems.addGlint(full);
        }
        content[13] = full;

        ItemStack miniatureBlockSize = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta miniatureBlockSizeMeta = miniatureBlockSize.getItemMeta();
        if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
            double blockSize = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize();
            miniatureBlockSizeMeta.setDisplayName("§7Miniature block size (" + blockSize + " block" + (blockSize == 1 ? "" : "s") + ")");
        }else{
            miniatureBlockSizeMeta.setDisplayName("§7Miniature block size §c(not set yet)");
        }
        miniatureBlockSize.setItemMeta(miniatureBlockSizeMeta);
        if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())) {
            if (factor == main.getMiniatureManager().getMiniature(player.getUniqueId()).getSize()) {
                FrequentItems.addGlint(miniatureBlockSize);
            }
        }
        content[14] = miniatureBlockSize;

        ItemStack custom = new ItemStack(Material.PAPER);
        ItemMeta customMeta = custom.getItemMeta();
        customMeta.setDisplayName("§7Custom factor §e(" + factor + " block" + (factor == 1 ? "" : "s") + ")");
        custom.setItemMeta(customMeta);
        boolean condition = factor != 0.25 && factor != 0.5 && factor != 1;
        if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
            condition = condition && factor != main.getMiniatureManager().getMiniature(player.getUniqueId()).getSize();
        }
        if(condition){
            FrequentItems.addGlint(custom);
        }
        content[15] = custom;

        FrequentItems.addAxisItems(content, axis);

        FrequentItems.addGeneralItems(content);

        return content;
    }

}
