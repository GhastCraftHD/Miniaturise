package de.leghast.miniaturise.ui.page;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.ui.Page;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SavedPage {

    public static ItemStack[] getSavedPage(Miniaturise main, Player player){
        ItemStack[] content = new ItemStack[45];

        PageUtil.addPageSwitchItems(content, Page.SAVED);


        return content;
    }

}
