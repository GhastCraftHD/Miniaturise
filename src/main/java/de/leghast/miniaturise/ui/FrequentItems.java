package de.leghast.miniaturise.ui;

import de.leghast.miniaturise.constant.Colors;
import de.leghast.miniaturise.manager.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FrequentItems {

    public static void addGlint(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
    }

    public static void addPageSwitchItems(ItemStack[] content, Page page){

        content[0] = new InterfaceItem(
                Material.MAGENTA_GLAZED_TERRACOTTA,
                Component.text("Position", Colors.ACCENT),
                () -> page == Page.POSITION,
                List.of(
                        Component.text("Adjust the position", NamedTextColor.GRAY),
                        Component.text("of the placed miniature", NamedTextColor.GRAY)
                )
        );

        content[9] = new InterfaceItem(
                Material.PUFFERFISH,
                Component.text("Size", Colors.ACCENT),
                () -> page == Page.SIZE,
                List.of(
                        Component.text("Adjust the size", NamedTextColor.GRAY),
                        Component.text("of the placed miniature", NamedTextColor.GRAY)
                )
        );
        content[18] = new InterfaceItem(
                Material.ITEM_FRAME,
                Component.text("Rotation", Colors.ACCENT),
                () -> page == Page.ROTATION,
                List.of(
                        Component.text("Adjust the rotation", NamedTextColor.GRAY),
                        Component.text("of the placed miniature", NamedTextColor.GRAY)
                )
        );
        
        content[27] = new InterfaceItem(
                Material.BAMBOO_HANGING_SIGN,
                Component.text("Saved miniatures", Colors.ACCENT),
                () -> page == Page.SAVED,
                List.of(
                        Component.text("View all your", NamedTextColor.GRAY),
                        Component.text("saved miniatures", NamedTextColor.GRAY)
                )
        );
        content[8] = new InterfaceItem(
                ConfigManager.ADJUSTER_TOOL,
                Component.text("Adjuster tool", NamedTextColor.GOLD),
                List.of(
                        Component.text("The selected adjusting", NamedTextColor.GRAY),
                        Component.text("settings are bound to this item", NamedTextColor.GRAY)
                )                
        );
        
    }

    public static List<Component> getLoaderLore(){
        return List.of(Component.text("Click to load", Colors.ACCENT));
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
            FrequentItems.addGlint(x);
        }
        content[30] = x;

        ItemStack y = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta yMeta = y.getItemMeta();
        yMeta.setDisplayName("§aY-Axis");
        y.setItemMeta(yMeta);
        if (axis == Axis.Y) {
            FrequentItems.addGlint(y);
        }
        content[31] = y;

        ItemStack z = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta zMeta = z.getItemMeta();
        zMeta.setDisplayName("§9Z-Axis");
        z.setItemMeta(zMeta);
        if (axis == Axis.Z) {
            FrequentItems.addGlint(z);
        }
        content[32] = z;
    }

}
