package de.leghast.miniaturise.ui;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.constant.Colors;
import de.leghast.miniaturise.settings.AdjusterSettings;
import net.kyori.adventure.text.Component;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class AnvilInputHelper {


    public static void getCustomNumberInput(Miniaturise main, Player player, Page page, double previousFactor){

        ItemStack output = new InterfaceItem(
                Material.PAPER,
                Component.text("Set custom factor", Colors.ACCENT),
                () -> true
        );

        new AnvilGUI.Builder()
                .title("§eEnter custom factor")
                .text(String.valueOf(previousFactor))
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
