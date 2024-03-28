package de.leghast.miniaturise.util;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.settings.AdjusterSettings;
import de.leghast.miniaturise.ui.Page;
import de.leghast.miniaturise.ui.FrequentItems;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Util {

    public static final String PREFIX = "§7[§eMiniaturise§7] ";

    public static void setCustomNumberInput(Miniaturise main, Player player, Page page){
        ItemStack output = new ItemStack(Material.PAPER);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName("§eSet custom factor");
        output.setItemMeta(meta);
        FrequentItems.addGlint(output);

        new AnvilGUI.Builder()
                .title("§eEnter custom factor")
                .text("1")
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                        switch (page){
                            case POSITION -> settings.getPositionSettings().setFactor(stateSnapshot.getText());
                            case SIZE -> settings.getSizeSettings().setFactor(stateSnapshot.getText());
                            case ROTATION -> settings.getRotationSettings().setFactor(stateSnapshot.getText());
                        }
                        return Arrays.asList(AnvilGUI.ResponseAction.close());
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.updateTitle("§eEnter custom factor", false));
                })
                .preventClose()
                .itemOutput(output)
                .plugin(main)
                .open(player);

    }

}
