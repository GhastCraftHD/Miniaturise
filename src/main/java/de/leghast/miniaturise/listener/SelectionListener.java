package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.manager.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SelectionListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(ConfigManager.getToolMaterial()) && e.getHand() == EquipmentSlot.HAND){
                e.setCancelled(true);
                switch (e.getAction()){
                    case LEFT_CLICK_BLOCK:
                        RegionManager.setLoc1(e.getClickedBlock().getLocation(), e.getPlayer());
                    case RIGHT_CLICK_BLOCK:
                        RegionManager.setLoc2(e.getClickedBlock().getLocation(), e.getPlayer());
                        
                }
        }

    }

}
