package de.leghast.miniaturise.constant;

import de.leghast.miniaturise.manager.ConfigManager;
import de.leghast.miniaturise.util.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Axis;
import org.bukkit.Location;

public class Message {

    public static final Component CLEARED_CLIPBOARD = Prefix.MINIATURISE.append(
            Component.text("You clipboard was cleared", Colors.SUCCESS)
    );

    public static final Component EMPTY_CLIPBOARD = Prefix.MINIATURISE.append(
            Component.text("You clipboard is empty", Colors.ERROR)
    );

    public static final Component NO_MINIATURE_IN_REGION = Prefix.MINIATURISE.append(
            Component.text("There is no miniature in the selected region", Colors.ERROR)
    );

    public static final Component SELECT_REGION_FIRST = Prefix.MINIATURISE.append(
            Component.text("Please select a region first", Colors.ERROR)
    );

    public static final Component SELECT_MINIATURE_FIRST = Prefix.MINIATURISE.append(
            Component.text("Please create a miniature first", Colors.ERROR)
    );

    public static final Component SELECT_PLACED_MINIATURE_FIRST = Prefix.MINIATURISE.append(
            Component.text("You have not placed/selected a placed miniature yet", Colors.ERROR)
    );

    public static final Component SELECT_LOCATIONS_FIRST = Prefix.MINIATURISE.append(
            Component.text("You have not selected any locations", Colors.ERROR)
    );

    public static final Component LOAD_COMMAND_USAGE = Prefix.MINIATURISE.append(
            Component.text("Usage: /mload <filename>", Colors.ERROR)
    );

    public static final Component ROTATE_COMMAND_USAGE = Prefix.MINIATURISE.append(
            Component.text("Usage /mrotate <axis> <angle>", Colors.ERROR)
    );

    public static final Component SAVE_COMMAND_USAGE = Prefix.MINIATURISE.append(
            Component.text("Usage: /msave <filename>", Colors.ERROR)
                    .append(Component.newline())
                    .append(Prefix.MINIATURISE)
                    .append(Component.text("The filename may not contain spaces", Colors.ACCENT))
    );

    public static final Component COULD_NOT_LOAD_MINIATURE = Prefix.MINIATURISE.append(
            Component.text("The miniature could not be loaded", Colors.ERROR)
    );

    public static final Component COULD_NOT_SAVE_MINIATURE = Prefix.MINIATURISE.append(
            Component.text("Could not save the miniature to a file", Colors.ERROR)
    );

    public static final Component MINIATURE_CONTAINS_NO_BLOCKS = Prefix.MINIATURISE.append(
            Component.text("The miniature in you clipboard contains no blocks", Colors.ERROR)
    );

    public static final Component SPECIFY_POSITIONS = Prefix.MINIATURISE.append(
            Component.text("Please specify a valid position", Colors.ERROR)
    );

    public static final Component INVALID_POSITION = Prefix.MINIATURISE.append(
            Component.text("Invalid position, please use 1 or 2", Colors.ERROR)
    );
    
    public static final Component INVALID_ANGLE = Prefix.MINIATURISE.append(
          Component.text("Please provide a valid angle", Colors.ERROR)
    );

    public static final Component INVALID_AXIS = Prefix.MINIATURISE.append(
            Component.text("Please provide a valid axis (x, y, z)", Colors.ERROR)
    );

    public static final Component INVALID_SCALE = Prefix.MINIATURISE.append(
            Component.text("Please provide a valid scale factor", Colors.ERROR)
    );

    public static final Component INVALID_LOCATIONS = Prefix.MINIATURISE.append(
            Component.text("Please select two locations", Colors.ERROR)
    );

    public static final Component PROVIDE_FILENAME = Prefix.MINIATURISE.append(
            Component.text("Please provide a name for the miniature", Colors.ERROR)
    );

    public static final Component SPECIFY_WHAT_TO_SCALE = Prefix.MINIATURISE.append(
            Component.text("Please specify, what you want to scale", Colors.ERROR)
    );

    public static final Component TOOLS = Prefix.MINIATURISE.append(
            Component.text("These items/blocks are currently bound to tools:", Colors.SUCCESS)
                    .append(Component.newline())
                    .append(Prefix.MINIATURISE)
                    .append(Component.text(" - Selector: ", Colors.SUCCESS))
                    .append(Component.text("minecraft:" + ConfigManager.SELECTOR_TOOL.name().toLowerCase(), Colors.ACCENT))
                    .append(Component.newline())
                    .append(Prefix.MINIATURISE)
                    .append(Component.text(" - Adjuster: ", Colors.SUCCESS))
                    .append(Component.text("minecraft:" + ConfigManager.ADJUSTER_TOOL.name().toLowerCase(), Colors.ACCENT))
    );

    public static Component copiedPlacedMiniature(int blockAmount){
        return Prefix.MINIATURISE.append(
                Component.text("The placed miniature was copied ", Colors.SUCCESS)
                        .append(Component.text("(" + blockAmount + " blocks)", Colors.ACCENT))
        );
    }

    public static Component cutRegion(int blockAmount){
        return Prefix.MINIATURISE.append(
                Component.text("The selected region was cut from the world", Colors.SUCCESS)
                        .append(Component.text("(" + blockAmount + " block" + (blockAmount == 1 ? "" : "s") + ")", Colors.ACCENT))
        );
    }

    public static Component loadedMiniatureFromFile(String name){
        return Prefix.MINIATURISE.append(
                Component.text(name, Colors.ACCENT)
                        .append(Component.text("was loaded to your clipboard", Colors.SUCCESS))
        );
    }

    public static Component couldNotFindFile(String name){
        return Prefix.MINIATURISE.append(
                Component.text("The file ", Colors.ERROR)
                        .append(Component.text(name, Colors.ACCENT))
                        .append(Component.text(" could not be found", Colors.ERROR))
        );
    }

    public static Component placedMiniature(Location location, int blockAmount){
        return Prefix.MINIATURISE.append(
                Component.text("The miniature was placed at ", Colors.SUCCESS)
                        .append(Component.text(
                                (int) location.getX() + ", " +
                                        (int) location.getY() + ", " +
                                        (int) location.getY(), Colors.ACCENT
                        ))
                        .append(Component.text(" (" + Util.getDimensionName(location.getWorld().getEnvironment().name()) + ")", Colors.SUCCESS))
                        .append(Component.text(" (" + blockAmount + " block" + (blockAmount == 1 ? "" : "s") + ")", Colors.ACCENT))
        );
    }


    public static Component selectedPosition(String specifier, Location location){
        return Prefix.MINIATURISE.append(
                Component.text("The " + specifier + " position was set to ", Colors.SUCCESS)
                        .append(Component.text(
                                (int) location.getX() + ", " +
                                        (int) location.getY() + ", " +
                                        (int) location.getY(), Colors.ACCENT
                        ))
                        .append(Component.text(" (" + Util.getDimensionName(location.getWorld().getEnvironment().name()) + ")", Colors.SUCCESS))
        );
    }

    public static Component removedPlacedMiniature(int blockAmount){
        return Prefix.MINIATURISE.append(
                Component.text("The placed miniature was deleted ", Colors.SUCCESS)
                        .append(Component.text("(" + blockAmount + " block" + (blockAmount == 1 ? "" : "s") + ")", Colors.ACCENT))
        );
    }

    public static Component rotatedPlacedMiniature(float angle, Axis axis){
        return Prefix.MINIATURISE.append(
                Component.text("The placed miniature was rotated by ", Colors.SUCCESS)
                        .append(Component.text(angle + " degrees", Colors.ACCENT))
                        .append(Component.text(" on the ", Colors.SUCCESS))
                        .append(Component.text(axis.name() + "-axis", Colors.ACCENT))
        );
    }

    public static Component fileAlreadyExists(String filename){
        return Prefix.MINIATURISE.append(
                Component.text("A miniature with the name ", Colors.SUCCESS)
                        .append(Component.text(filename + ".mcminiature", Colors.ACCENT))
                        .append(Component.text(" already exists", Colors.SUCCESS))
                        .append(Component.newline())
                        .append(Prefix.MINIATURISE)
                        .append(Component.text("Click here to overwrite ", Colors.SUCCESS))
                        .append(Component.text("[OVERWRITE]", Colors.ACCENT)
                                .hoverEvent(HoverEvent.showText(Component.text("Overwrite " + filename + ".mcminiature", Colors.ACCENT)))
                                .clickEvent(ClickEvent.runCommand("/msave " + filename + " confirm")))
        );
    }

    public static Component savedMiniature(String filename){
        return Prefix.MINIATURISE.append(
                Component.text("The miniature was saved as ", Colors.SUCCESS)
                        .append(Component.text(filename + ".mcminiature", Colors.ACCENT))
        );
    }

    public static Component overwroteMiniature(String filename){
        return Prefix.MINIATURISE.append(
                Component.text(filename + ".mcminiature", Colors.ACCENT)
                        .append(Component.text(" was overwritten", Colors.SUCCESS))
        );
    }

    public static Component scaledMiniature(double newSize){
        return Prefix.MINIATURISE.append(
                Component.text("The selected Miniature was scaled to ", Colors.SUCCESS)
                        .append(Component.text(newSize, Colors.ACCENT))
                        .append(Component.text(" blocks", Colors.SUCCESS))
        );
    }

    public static Component aboveEntityLimit(int blockAmount){
        return Prefix.MINIATURISE.append(
                Component.text("The current selection ", Colors.ERROR)
                        .append(Component.text("(" + blockAmount + " blocks)", Colors.ACCENT))
                        .append(Component.text(" exceeds the limit of ", Colors.ERROR))
                        .append(Component.text(ConfigManager.MAX_ENTITY_LIMIT, Colors.ACCENT))
                        .append(Component.text(" blocks", Colors.ERROR))
        );
    }

    public static Component selectedRegion(int blockAmount){
        return Prefix.MINIATURISE.append(
                Component.text("The region was selected ", Colors.SUCCESS)
                        .append(Component.text("(" + blockAmount +
                                " block" + (blockAmount == 1 ? "" : "s") + ")", Colors.ACCENT))
        );
    }

}
