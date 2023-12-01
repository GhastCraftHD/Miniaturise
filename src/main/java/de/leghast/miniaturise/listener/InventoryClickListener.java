package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private Miniaturise main;

    public InventoryClickListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

    }

}
