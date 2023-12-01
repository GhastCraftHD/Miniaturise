package de.leghast.miniaturise.ui;

import de.leghast.miniaturise.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DefaultInterface {

    public static Inventory getDefaultInventory(Player player){
        Inventory inv = Bukkit.createInventory(player, 45);

        ItemStack position = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
        ItemMeta positionMeta = position.getItemMeta();
        positionMeta.setDisplayName("§ePosition");
        List<String> positionLore = new ArrayList<>();
        positionLore.add("§7Adjust the position");
        positionLore.add("§7of the placed miniature");
        positionMeta.setLore(positionLore);
        position.addEnchantment(Enchantment.LURE, 1);
        positionMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        position.setItemMeta(positionMeta);
        inv.setItem(0, position);

        ItemStack size = new ItemStack(Material.PUFFERFISH);
        ItemMeta sizeMeta = size.getItemMeta();
        sizeMeta.setDisplayName("§eSize");
        List<String> sizeLore = new ArrayList<>();
        sizeLore.add("§7Adjust the size");
        sizeLore.add("§7of the placed miniature");
        sizeMeta.setLore(sizeLore);
        size.setItemMeta(sizeMeta);
        inv.setItem(9, size);

        ItemStack adjuster = new ItemStack(ConfigManager.getAdjusterToolMaterial());
        ItemMeta adjusterMeta = size.getItemMeta();
        adjusterMeta.setDisplayName("§6Adjuster Tool");
        List<String> adjusterLore = new ArrayList<>();
        adjusterLore.add("§7The selected adjusting");
        adjusterLore.add("§7settings are bound to this item");
        adjusterMeta.setLore(adjusterLore);
        adjuster.setItemMeta(adjusterMeta);
        inv.setItem(8, adjuster);

        return inv;
    }

}
