package de.leghast.miniaturise.listener;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
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

        Bukkit.getScheduler().runTaskLaterAsynchronously(main,
                () -> {
                    if(player.isOp()){
                        if(main.isUpdateAvailable()){
                            player.sendMessage(Message.newVersionAvailable(main.getLatestReleaseVersion()));
                        }
                    }
                }, 10L
        );

    }

}
