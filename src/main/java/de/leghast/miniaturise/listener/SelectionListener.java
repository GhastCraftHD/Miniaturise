package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.region.RegionManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SelectionListener implements Listener {

    @EventHandler
    public void onLeftClick(BlockBreakEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_SHOVEL){
            e.setCancelled(true);
            RegionManager.setLoc1(e.getBlock().getLocation(), e.getPlayer());
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WOODEN_SHOVEL
                && e.getAction() == Action.RIGHT_CLICK_BLOCK
                && e.getHand() == EquipmentSlot.HAND){
                RegionManager.setLoc2(e.getClickedBlock().getLocation(), e.getPlayer());
        }

    }

}
