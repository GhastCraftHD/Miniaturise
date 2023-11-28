package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.SelectedLocations;
import de.leghast.miniaturise.manager.ConfigManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * This class listens for interactions, that are relevant for the Miniaturise plugin
 * @author GhastCraftHD
 * */
public class SelectorListener implements Listener {

    private Miniaturise main;

    public SelectorListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(player.getInventory().getItemInMainHand().getType() == ConfigManager.getSelectorToolMaterial()){
            e.setCancelled(true);
            switch (e.getAction()){
                case LEFT_CLICK_BLOCK -> {
                    if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                        main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc1(e.getClickedBlock().getLocation());
                    } else {
                        main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(e.getClickedBlock().getLocation(), null));
                    }
                    player.sendMessage(main.PREFIX + "§aThe first location was set to §e" +
                            (int) e.getClickedBlock().getLocation().getX() + ", " +
                            (int) e.getClickedBlock().getLocation().getY() + ", " +
                            (int) e.getClickedBlock().getLocation().getZ() + " §a(" +
                            main.getDimensionName(e.getClickedBlock().getLocation().getWorld().getEnvironment().name()) + ")");
                }
                case RIGHT_CLICK_BLOCK -> {
                    if(e.getHand() == EquipmentSlot.HAND){
                        if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                            main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc2(e.getClickedBlock().getLocation());
                        } else {
                            main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(null, e.getClickedBlock().getLocation()));
                        }
                        player.sendMessage(main.PREFIX + "§aThe second location was set to §e" +
                                (int) e.getClickedBlock().getLocation().getX() + ", " +
                                (int) e.getClickedBlock().getLocation().getY() + ", " +
                                (int) e.getClickedBlock().getLocation().getZ()+ " §a(" +
                                main.getDimensionName(e.getClickedBlock().getLocation().getWorld().getEnvironment().name()) + ")");
                    }
                }
            }
        }
    }
}
