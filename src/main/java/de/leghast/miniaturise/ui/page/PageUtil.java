package de.leghast.miniaturise.ui.page;

import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.ui.Page;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    public static void addGlint(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
    }

    public static void addPageSwitchItems(ItemStack[] content, Page page){
        ItemStack position = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
        ItemMeta positionMeta = position.getItemMeta();
        positionMeta.setDisplayName("§ePosition");
        List<String> positionLore = new ArrayList<>();
        positionLore.add("§7Adjust the position");
        positionLore.add("§7of the placed miniature");
        positionMeta.setLore(positionLore);
        position.setItemMeta(positionMeta);
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

        ItemStack rotation = new ItemStack(Material.ITEM_FRAME);
        ItemMeta rotationMeta = rotation.getItemMeta();
        rotationMeta.setDisplayName("§eRotation");
        List<String> rotationLore = new ArrayList<>();
        rotationLore.add("§7Adjust the rotation");
        rotationLore.add("§7of the placed miniature");
        rotationMeta.setLore(rotationLore);
        rotation.setItemMeta(rotationMeta);
        content[18] = rotation;

        ItemStack adjuster = new ItemStack(ConfigManager.getAdjusterToolMaterial());
        ItemMeta adjusterMeta = size.getItemMeta();
        adjusterMeta.setDisplayName("§6Adjuster Tool");
        List<String> adjusterLore = new ArrayList<>();
        adjusterLore.add("§7The selected adjusting");
        adjusterLore.add("§7settings are bound to this item");
        adjusterMeta.setLore(adjusterLore);
        adjuster.setItemMeta(adjusterMeta);
        content[8] = adjuster;

        switch(page){
            case POSITION -> PageUtil.addGlint(position);
            case SIZE -> PageUtil.addGlint(size);
            case ROTATION -> PageUtil.addGlint(rotation);
        }
    }

    public static void addGeneralItems(ItemStack[] content){
        ItemStack delete = new ItemStack(Material.BARRIER);
        ItemMeta deleteMeta = delete.getItemMeta();
        deleteMeta.setDisplayName("§cDelete miniature");
        delete.setItemMeta(deleteMeta);
        content[44] = delete;

        ItemStack deselect = new ItemStack(Material.STRUCTURE_VOID);
        ItemMeta deselectMeta = deselect.getItemMeta();
        deselectMeta.setDisplayName("§cDeselect miniature");
        deselect.setItemMeta(deselectMeta);
        content[26] = deselect;

        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        for(int i = 0; i < content.length; i++){
            if(content[i] == null){
                content[i] = filler;
            }
        }
    }

    public static void addAxisItems(ItemStack[] content, Axis axis) {
        ItemStack x = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta xMeta = x.getItemMeta();
        xMeta.setDisplayName("§cX-Axis");
        x.setItemMeta(xMeta);
        if (axis == Axis.X) {
            PageUtil.addGlint(x);
        }
        content[30] = x;

        ItemStack y = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta yMeta = y.getItemMeta();
        yMeta.setDisplayName("§aY-Axis");
        y.setItemMeta(yMeta);
        if (axis == Axis.Y) {
            PageUtil.addGlint(y);
        }
        content[31] = y;

        ItemStack z = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta zMeta = z.getItemMeta();
        zMeta.setDisplayName("§9Z-Axis");
        z.setItemMeta(zMeta);
        if (axis == Axis.Z) {
            PageUtil.addGlint(z);
        }
        content[32] = z;
    }

}
