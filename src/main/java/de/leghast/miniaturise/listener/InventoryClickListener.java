package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.handler.PositionInteractionHandler;
import de.leghast.miniaturise.handler.RotationInteractionHandler;
import de.leghast.miniaturise.handler.SaveInteractionHandler;
import de.leghast.miniaturise.handler.SizeInteractionHandler;
import de.leghast.miniaturise.ui.Page;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final Miniaturise main;

    public InventoryClickListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Component title = e.getView().title();

        if(title.contains(Page.POSITION.getTitle())){
            new PositionInteractionHandler(main, e.getRawSlot(), player);
            e.setCancelled(true);
        }else if(title.contains(Page.SIZE.getTitle())){
            new SizeInteractionHandler(main, e.getRawSlot(), player);
            e.setCancelled(true);
        }else if(title.contains(Page.ROTATION.getTitle())){
            new RotationInteractionHandler(main, e.getRawSlot(), player);
            e.setCancelled(true);
        }else if(title.contains(Page.SAVED.getTitle())){
            new SaveInteractionHandler(main, e.getRawSlot(), player, e.getInventory());
            e.setCancelled(true);
        }
    }

}
