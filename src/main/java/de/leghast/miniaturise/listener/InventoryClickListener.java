package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.miniature.Miniature;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.settings.AdjusterSettings;
import de.leghast.miniaturise.settings.DimensionSettings;
import de.leghast.miniaturise.settings.FactorSettings;
import de.leghast.miniaturise.ui.UserInterface;
import de.leghast.miniaturise.ui.Page;
import de.leghast.miniaturise.ui.page.PageUtil;
import de.leghast.miniaturise.util.Util;
import org.bukkit.Axis;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private final Miniaturise main;

    public InventoryClickListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();

        if(title.contains(Page.POSITION.getTitle())){
            handlePositionInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        }else if(title.contains(Page.SIZE.getTitle())){
            handleSizeInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        }else if(title.contains(Page.ROTATION.getTitle())){
            handleRotationInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        }else if(title.contains(Page.SAVED.getTitle())){
            handleSavedInteractions(e.getRawSlot(), player, e.getInventory());
            e.setCancelled(true);
        }
    }

    private void handlePositionInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings positionSettings = settings.getPositionSettings();

        switch (slot){
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.ADJUSTER_TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            //case 27 -> settings.setPage(Page.SAVED);
            case 11 -> positionSettings.setFactor(0.25);
            case 12 -> positionSettings.setFactor(0.5);
            case 13 -> positionSettings.setFactor(1);
            case 14 -> positionSettings.setFactor(main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize());
            case 15 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 30 -> positionSettings.setAxis(Axis.X);
            case 31 -> positionSettings.setAxis(Axis.Y);
            case 32 -> positionSettings.setAxis(Axis.Z);
            case 26 -> {
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).remove();
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleSizeInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        FactorSettings sizeSettings = settings.getSizeSettings();

        switch (slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.ADJUSTER_TOOL));
            case 18 -> settings.setPage(Page.ROTATION);
            //case 27 -> settings.setPage(Page.SAVED);
            case 20 -> sizeSettings.setFactor(0.25);
            case 21 -> sizeSettings.setFactor(0.5);
            case 22 -> sizeSettings.setFactor(1);
            case 23 -> sizeSettings.setFactor(main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize());
            case 24 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 26 -> {
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).remove();
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleRotationInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings rotationSettings = settings.getRotationSettings();

        switch (slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.ADJUSTER_TOOL));
            case 9 -> settings.setPage(Page.SIZE);
            //case 27 -> settings.setPage(Page.SAVED);
            case 11 -> rotationSettings.setFactor(22.5);
            case 12 -> rotationSettings.setFactor(45);
            case 13 -> rotationSettings.setFactor(90);
            case 14 -> rotationSettings.setFactor(180);
            case 15 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 30 -> rotationSettings.setAxis(Axis.X);
            case 31 -> rotationSettings.setAxis(Axis.Y);
            case 32 -> rotationSettings.setAxis(Axis.Z);
            case 26 -> {
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).remove();
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleSavedInteractions(int slot, Player player, Inventory inv){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 26 -> {
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).remove();
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            default -> {
                ItemStack itemStack = inv.getItem(slot);
                if(itemStack != null){
                    if(itemStack.getItemMeta().getLore() == PageUtil.getLoaderLore()){
                        try {
                            Miniature miniature = main.getSaveManager().deserialize(itemStack.getItemMeta().getDisplayName());
                            main.getMiniatureManager().addMiniature(player.getUniqueId(), miniature);
                            player.sendMessage(Util.PREFIX + "§aThe miniature §e" + itemStack.getItemMeta().getDisplayName() + ".mcminiature §a was loaded to your clipboard");
                        } catch (Exception e) {
                            player.sendMessage(Util.PREFIX + "§cThe miniature could not be loaded");
                        }
                        player.closeInventory();
                    }
                }
            }
        }
        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

}
