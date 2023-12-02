package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.settings.AdjusterSettings;
import de.leghast.miniaturise.instance.settings.PositionSettings;
import de.leghast.miniaturise.instance.settings.SizeSettings;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.instance.settings.Axis;
import de.leghast.miniaturise.ui.Interface;
import de.leghast.miniaturise.ui.Page;
import de.leghast.miniaturise.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
        }

    }

    private void handlePositionInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        PositionSettings positionSettings = settings.getPositionSettings();

        switch (slot){
            case 8 -> {
                player.getInventory().addItem(new ItemStack(ConfigManager.getAdjusterToolMaterial()));
            }
            case 9 -> {
                settings.setPage(Page.SIZE);
            }
            case 11 -> {
                positionSettings.setFactor(0.25);
            }
            case 12 -> {
                positionSettings.setFactor(0.5);
            }
            case 13 -> {
                positionSettings.setFactor(1);
            }
            case 14 -> {
                positionSettings.setFactor(main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize());
            }
            case 15 -> {
                Util.setCustomNumberInput(main, player, settings.getPage());
            }
            case 30 -> {
                positionSettings.setAxis(Axis.X);
            }
            case 31 -> {
                positionSettings.setAxis(Axis.Y);
            }
            case 32 -> {
                positionSettings.setAxis(Axis.Z);
            }
        }

        new Interface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
    }

    private void handleSizeInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        SizeSettings sizeSettings = settings.getSizeSettings();

        switch (slot){
            case 0 -> {
                settings.setPage(Page.POSITION);
            }
            case 8 -> {
                player.getInventory().addItem(new ItemStack(ConfigManager.getAdjusterToolMaterial()));
            }
            case 20 -> {
                sizeSettings.setFactor(0.25);
            }
            case 21 -> {
                sizeSettings.setFactor(0.5);
            }
            case 22 -> {
                sizeSettings.setFactor(1);
            }
            case 23 -> {
                sizeSettings.setFactor(main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).getBlockSize());
            }
            case 24 -> {
                Util.setCustomNumberInput(main, player, settings.getPage());
            }
        }

        new Interface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
    }

}
