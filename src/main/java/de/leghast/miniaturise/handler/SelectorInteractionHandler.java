package de.leghast.miniaturise.handler;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.region.SelectedLocations;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;

public class SelectorInteractionHandler {

    public SelectorInteractionHandler(Miniaturise main, Player player, Action action, Block block, EquipmentSlot hand){
        switch (action){
            case LEFT_CLICK_BLOCK -> {
                if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                    main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc1(block.getLocation());
                } else {
                    main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(block.getLocation(), null));
                }
                player.sendMessage(Message.selectedPosition("first", block.getLocation()));
            }
            case RIGHT_CLICK_BLOCK -> {
                if(hand == EquipmentSlot.HAND){
                    if (main.getRegionManager().hasSelectedLocations(player.getUniqueId())) {
                        main.getRegionManager().getSelectedLocations(player.getUniqueId()).setLoc2(block.getLocation());
                    } else {
                        main.getRegionManager().addSelectedLocations(player.getUniqueId(), new SelectedLocations(null, block.getLocation()));
                    }
                    player.sendMessage(Message.selectedPosition("second", block.getLocation()));
                }
            }
        }
    }

}
