package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.manager.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SelectionListener implements Listener {

    private Miniaturise main;

    public SelectionListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(main.getConfigManager().getToolMaterial())){
            e.setCancelled(true);
            main.getRegionManager().setLoc1(e.getBlock().getLocation(), e.getPlayer());
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(main.getConfigManager().getToolMaterial()) && e.getHand() == EquipmentSlot.HAND && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                e.setCancelled(true);
                    main.getRegionManager().setLoc2(e.getClickedBlock().getLocation(), e.getPlayer());

        }

    }

}
