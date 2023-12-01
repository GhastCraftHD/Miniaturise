package de.leghast.miniaturise.ui;

import de.leghast.miniaturise.command.AdjustCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PositionInterface {

    private AdjustCommand adjustCommand;

    public PositionInterface(AdjustCommand adjustCommand){
        this.adjustCommand = adjustCommand;
    }

    public Inventory getPositionInventory(Player player){
        Inventory inv = Bukkit.createInventory(player, 45, "§eAdjust miniature position");

        for(ItemStack item : DefaultInterface.getDefaultInventory(player).getContents()){
            for(int i = 0; i < 45; i++){
                inv.setItem(i, item);
            }
        }

        ItemStack quarter = new ItemStack(Material.COAL);
        ItemMeta quarterMeta = quarter.getItemMeta();
        quarterMeta.setDisplayName("§70.25 blocks");
        quarter.setItemMeta(quarterMeta);
        inv.setItem(11, quarter);

        ItemStack half = new ItemStack(Material.IRON_INGOT);
        ItemMeta halfMeta = half.getItemMeta();
        halfMeta.setDisplayName("§70.5 blocks");
        half.setItemMeta(halfMeta);
        inv.setItem(12, half);

        ItemStack full = new ItemStack(Material.DIAMOND);
        ItemMeta fullMeta = full.getItemMeta();
        fullMeta.setDisplayName("§71 block");
        full.setItemMeta(fullMeta);
        inv.setItem(13, full);

        ItemStack miniatureBlockSize = new ItemStack(Material.DIAMOND);
        ItemMeta miniatureBlockSizeMeta = miniatureBlockSize.getItemMeta();
        if(adjustCommand.getMain().getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
            double blockSize = adjustCommand.getMain().getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize();
            miniatureBlockSizeMeta.setDisplayName("§7Miniature block size (" + blockSize + " block" + (blockSize == 1 ? "" : "s") + ")");
        }else{
            miniatureBlockSizeMeta.setDisplayName("§cNaN");
        }
        miniatureBlockSize.setItemMeta(miniatureBlockSizeMeta);
        inv.setItem(13, miniatureBlockSize);



        return inv;
    }

}
