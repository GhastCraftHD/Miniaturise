package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.miniature.PlacedMiniature;
import de.leghast.miniaturise.instance.region.SelectedLocations;
import de.leghast.miniaturise.instance.settings.AdjusterSettings;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.util.Util;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * This class listens for player interactions, that are relevant for the Miniaturise plugin
 * @author GhastCraftHD
 * */
public class PlayerInteractListener implements Listener {

    private Miniaturise main;

    public PlayerInteractListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();

        if(material == ConfigManager.getSelectorToolMaterial()){
            e.setCancelled(true);
            handleSelectorInteraction(player, e.getAction(), e.getClickedBlock(), e.getHand());
        }else if(material == ConfigManager.getAdjusterToolMaterial()){
            e.setCancelled(true);
            if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
                handleAdjusterInteraction(player, e.getAction(), e.getHand());
            }else{
                player.sendMessage(Util.PREFIX + "§cYou have not selected a placed miniature");
            }
        }
    }

    private void handleSelectorInteraction(Player player, Action action, Block block, EquipmentSlot hand){
        switch (action){
            case LEFT_CLICK_BLOCK -> {
                if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                    main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc1(block.getLocation());
                } else {
                    main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(block.getLocation(), null));
                }
                player.sendMessage(Util.PREFIX + "§aThe first position was set to §e" +
                        (int) block.getLocation().getX() + ", " +
                        (int) block.getLocation().getY() + ", " +
                        (int) block.getLocation().getZ() + " §a(" +
                        Util.getDimensionName(block.getLocation().getWorld().getEnvironment().name()) + ")");
            }
            case RIGHT_CLICK_BLOCK -> {
                if(hand == EquipmentSlot.HAND){
                    if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                        main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc2(block.getLocation());
                    } else {
                        main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(null, block.getLocation()));
                    }
                    player.sendMessage(Util.PREFIX + "§aThe second position was set to §e" +
                            (int) block.getLocation().getX() + ", " +
                            (int) block.getLocation().getY() + ", " +
                            (int) block.getLocation().getZ()+ " §a(" +
                            Util.getDimensionName(block.getLocation().getWorld().getEnvironment().name()) + ")");
                }
            }
        }
    }

    private void handleAdjusterInteraction(Player player, Action action, EquipmentSlot hand){
        if(!main.getSettingsManager().hasAdjusterSettings(player.getUniqueId())){
            main.getSettingsManager().addAdjusterSettings(player.getUniqueId());
        }
        if(action.isLeftClick()){
            PlacedMiniature placedMiniature = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId());
            AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
            switch (main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage()){
                case POSITION -> {
                    placedMiniature.move(settings.getPositionSettings().getAxis(), -settings.getPositionSettings().getFactor());
                }
                case SIZE -> {
                    placedMiniature.scaleDown(settings.getSizeSettings().getFactor());
                }
            }
        }else if(action.isRightClick()){
            if(hand == EquipmentSlot.HAND){
                PlacedMiniature placedMiniature = main.getMiniatureManager().getPlacedMiniature(player.getUniqueId());
                AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                switch (main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage()){
                    case POSITION -> {
                        placedMiniature.move(settings.getPositionSettings().getAxis(), settings.getPositionSettings().getFactor());
                    }
                    case SIZE -> {
                        placedMiniature.scaleUp(settings.getSizeSettings().getFactor());
                    }
                }
            }
        }
    }


}
