package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.manager.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SelectionListener implements Listener {



    @EventHandler
    public void onLeftClick(BlockBreakEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(ConfigManager.getToolMaterial())){
            e.setCancelled(true);
            RegionManager.setLoc1(e.getBlock().getLocation(), e.getPlayer());
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(ConfigManager.getToolMaterial())
                && e.getAction() == Action.RIGHT_CLICK_BLOCK
                && e.getHand() == EquipmentSlot.HAND){
                e.setCancelled(true);
                RegionManager.setLoc2(e.getClickedBlock().getLocation(), e.getPlayer());
        }

    }

}
