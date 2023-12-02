package de.leghast.miniaturise.util;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.instance.region.Region;
import de.leghast.miniaturise.instance.settings.AdjusterSettings;
import de.leghast.miniaturise.instance.settings.PositionSettings;
import de.leghast.miniaturise.ui.Page;
import de.leghast.miniaturise.ui.page.PageUtil;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Util {

    public static final String PREFIX = "§7[§eMiniaturise§7] ";

    public static String getDimensionName(String string){
        switch (string){
            case "NORMAL" -> {
                return "minecraft:overworld";
            }
            case "NETHER" -> {
                return "minecraft:the_nether";
            }
            case "THE_END" -> {
                return "minecraft:the_end";
            }
            default -> {
                return "Invalid dimension";
            }
        }
    }

    public static List<BlockDisplay> getBlockDisplaysFromRegion(Player player, Region region){
        List<BlockDisplay> blockDisplays = new ArrayList<>();
        for(Chunk chunk : player.getWorld().getLoadedChunks()){
            for(Entity entity : chunk.getEntities()){
                if(entity instanceof BlockDisplay && region.contains(entity.getLocation())){
                    blockDisplays.add((BlockDisplay) entity);
                }
            }
        }
        return blockDisplays;
    }

    public static void setCustomNumberInput(Miniaturise main, Player player, Page page){
        ItemStack output = new ItemStack(Material.PAPER);
        ItemMeta meta = output.getItemMeta();
        meta.setDisplayName("§eSet custom factor");
        output.setItemMeta(meta);
        PageUtil.addGlint(output);

        new AnvilGUI.Builder()
                .title("§eEnter custom factor")
                .text("1")
                .onClick((slot, stateSnapshot) -> {
                    if(slot == AnvilGUI.Slot.OUTPUT){
                        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
                        switch (page){
                            case POSITION -> {
                                settings.getPositionSettings().setFactor(stateSnapshot.getText());
                            }
                            case SIZE -> {
                                settings.getSizeSettings().setFactor(stateSnapshot.getText());
                            }
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
