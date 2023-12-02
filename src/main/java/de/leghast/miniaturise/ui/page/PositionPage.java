package de.leghast.miniaturise.ui.page;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.instance.settings.Axis;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PositionPage {

    public static ItemStack[] getPositionPage(Miniaturise main, Player player){
        ItemStack[] content = new ItemStack[45];

        double factor = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getFactor();
        Axis axis = main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPositionSettings().getAxis();

        ItemStack position = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
        ItemMeta positionMeta = position.getItemMeta();
        positionMeta.setDisplayName("§ePosition");
        List<String> positionLore = new ArrayList<>();
        positionLore.add("§7Adjust the position");
        positionLore.add("§7of the placed miniature");
        positionMeta.setLore(positionLore);
        position.setItemMeta(positionMeta);
        PageUtil.addGlint(position);
        content[0] = position;

        ItemStack size = new ItemStack(Material.PUFFERFISH);
        ItemMeta sizeMeta = size.getItemMeta();
        sizeMeta.setDisplayName("§eSize");
        List<String> sizeLore = new ArrayList<>();
        sizeLore.add("§7Adjust the size");
        sizeLore.add("§7of the placed miniature");
        sizeMeta.setLore(sizeLore);
        size.setItemMeta(sizeMeta);
        content[9] = size;

        ItemStack adjuster = new ItemStack(ConfigManager.getAdjusterToolMaterial());
        ItemMeta adjusterMeta = size.getItemMeta();
        adjusterMeta.setDisplayName("§6Adjuster Tool");
        List<String> adjusterLore = new ArrayList<>();
        adjusterLore.add("§7The selected adjusting");
        adjusterLore.add("§7settings are bound to this item");
        adjusterMeta.setLore(adjusterLore);
        adjuster.setItemMeta(adjusterMeta);
        content[8] = adjuster;

        ItemStack quarter = new ItemStack(Material.COAL);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.setDisplayName("§70.25 blocks");
        quarter.setItemMeta(quarterMeta);
        if(factor == 0.25){
            PageUtil.addGlint(quarter);
        }
        content[11] = quarter;

        ItemStack half = new ItemStack(Material.IRON_INGOT);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.setDisplayName("§70.5 blocks");
        half.setItemMeta(halfMeta);
        if(factor == 0.5){
            PageUtil.addGlint(half);
        }
        content[12] = half;

        ItemStack full = new ItemStack(Material.DIAMOND);
        ItemMeta fullMeta = full.getItemMeta();
        fullMeta.setDisplayName("§71 block");
        full.setItemMeta(fullMeta);
        if(factor == 1){
            PageUtil.addGlint(full);
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
                PageUtil.addGlint(miniatureBlockSize);
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
            PageUtil.addGlint(custom);
        }
        content[15] = custom;

        ItemStack x = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta xMeta = x.getItemMeta();
        xMeta.setDisplayName("§cX-Axis");
        x.setItemMeta(xMeta);
        if(axis == Axis.X){
            PageUtil.addGlint(x);
        }
        content[30] = x;

        ItemStack y = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta yMeta = y.getItemMeta();
        yMeta.setDisplayName("§aY-Axis");
        y.setItemMeta(yMeta);
        if(axis == Axis.Y){
            PageUtil.addGlint(y);
        }
        content[31] = y;

        ItemStack z = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta zMeta = z.getItemMeta();
        zMeta.setDisplayName("§9Z-Axis");
        z.setItemMeta(zMeta);
        if(axis == Axis.Z){
            PageUtil.addGlint(z);
        }
        content[32] = z;

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        for(int i = 0; i < content.length; i++){
            if(content[i] == null){
                content[i] = filler;
            }
        }

        return content;
    }

}
