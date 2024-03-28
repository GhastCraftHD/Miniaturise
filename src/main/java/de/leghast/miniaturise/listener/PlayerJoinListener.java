package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Miniaturise main;

    public PlayerJoinListener(Miniaturise main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(player.isOp()){
            if(main.isUpdateAvailable()){
                player.sendMessage(Message.newVersionAvailable(main.getLatestReleaseVersion()));
            }
        }

    }

}
