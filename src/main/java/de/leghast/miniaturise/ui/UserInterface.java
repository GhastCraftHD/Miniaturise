package de.leghast.miniaturise.ui;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.ui.page.PositionPage;
import de.leghast.miniaturise.ui.page.SizePage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UserInterface {

    public UserInterface(Miniaturise main, Player player, Page page){
        Inventory inv = Bukkit.createInventory(null, 45, page.getTitle());
        switch (page){
            case POSITION -> {
                inv.setContents(PositionPage.getPositionPage(main, player));
            }
            case SIZE -> {
                inv.setContents(SizePage.getSizePage(main, player));
            }
        }
        player.openInventory(inv);
    }

}
