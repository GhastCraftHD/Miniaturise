package de.leghast.miniaturise.constant;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Prefix {

    public static final Component MINIATURISE = Component.text("[", NamedTextColor.GRAY)
            .append(Component.text("Miniaturise", Colors.ACCENT))
            .append(Component.text("] ", NamedTextColor.GRAY));

}
