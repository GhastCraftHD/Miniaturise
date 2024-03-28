package de.leghast.miniaturise.handler;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Message;
import de.leghast.miniaturise.miniature.Miniature;
import de.leghast.miniaturise.settings.AdjusterSettings;
import de.leghast.miniaturise.ui.Page;
import de.leghast.miniaturise.ui.UserInterface;
import de.leghast.miniaturise.ui.FrequentItems;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class SaveInteractionHandler {

    public SaveInteractionHandler(Miniaturise main, int slot, Player player, Inventory inv){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());

        switch(slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 26 -> {
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getMiniatureManager().getPlacedMiniature(player.getUniqueId()).remove();
                main.getMiniatureManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            default -> {
                ItemStack itemStack = inv.getItem(slot);
                if(itemStack == null) return;

                String filename = ((TextComponent) itemStack.displayName()).content();
                if(Objects.equals(itemStack.lore(), FrequentItems.getLoaderLore())){
                    try {
                        Miniature miniature = main.getSaveManager().deserialize(filename);
                        main.getMiniatureManager().addMiniature(player.getUniqueId(), miniature);
                        player.sendMessage(Message.loadedMiniatureFromFile(filename));
                    } catch (Exception e) {
                        player.sendMessage(Message.COULD_NOT_LOAD_MINIATURE);
                    }
                    player.closeInventory();
                }

            }
        }
        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

}
