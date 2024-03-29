package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.handler.AdjusterInteractionHandler;
import de.leghast.miniaturise.handler.SelectorInteractionHandler;
import de.leghast.miniaturise.manager.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * This class listens for player interactions, that are relevant for the Miniaturise plugin
 * @author GhastCraftHD
 * */
public class PlayerInteractListener implements Listener {

    private final Miniaturise main;

    public PlayerInteractListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();

        if(!player.hasPermission(Miniaturise.PERMISSION)) return;

        if(material == ConfigManager.SELECTOR_TOOL){
            e.setCancelled(true);
            new SelectorInteractionHandler(main, player, e.getAction(), e.getClickedBlock(), e.getHand());
        }else if(material == ConfigManager.ADJUSTER_TOOL){
            if(main.getMiniatureManager().hasPlacedMiniature(player.getUniqueId())){
                e.setCancelled(true);
                new AdjusterInteractionHandler(main, player, e.getAction(), e.getHand());
            }else{
                player.sendMessage(Message.SELECT_PLACED_MINIATURE_FIRST);
            }
        }

    }

}
