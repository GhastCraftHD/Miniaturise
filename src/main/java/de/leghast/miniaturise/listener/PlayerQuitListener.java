package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    private Miniaturise main;

    public PlayerQuitListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        main.getRegionManager().removeClipboard(uuid);
        main.getMiniatureManager().removeClipboard(uuid);
        main.getSettingsManager().removeAdjusterSettings(uuid);
    }

}
