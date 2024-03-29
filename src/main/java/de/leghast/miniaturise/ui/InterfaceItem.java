package de.leghast.miniaturise.ui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class InterfaceItem extends ItemStack {

    public InterfaceItem(Material material, Component displayName){
        super(material);
        ItemMeta meta = super.getItemMeta();
        meta.displayName(displayName.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE));
        super.setItemMeta(meta);
    }

    public InterfaceItem(Material material, Component displayName, List<Component> lore){
        this(material, displayName);
        ItemMeta meta = super.getItemMeta();
        if(!lore.isEmpty()){
            meta.lore(lore.stream().map(val -> val.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)).collect(Collectors.toList()));
        }
        super.setItemMeta(meta);
    }

    public InterfaceItem(Material material, Component displayName, GlowPredicate glowPredicate){
        this(material, displayName);
        if(glowPredicate.test()) FrequentItems.addGlint(this);
    }

    public InterfaceItem(Material material, Component displayName, GlowPredicate glowPredicate, List<Component> lore){
        this(material, displayName, glowPredicate);
        ItemMeta meta = super.getItemMeta();
        if(!lore.isEmpty()){
            meta.lore(lore.stream().map(val -> val.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)).collect(Collectors.toList()));
        }
        super.setItemMeta(meta);
    }

}
