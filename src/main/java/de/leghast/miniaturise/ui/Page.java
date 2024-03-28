package de.leghast.miniaturise.ui;

import de.leghast.miniaturise.constant.Colors;
import net.kyori.adventure.text.Component;

public enum Page {

    POSITION(Component.text("Adjust miniature position", Colors.ACCENT)),
    SIZE(Component.text("Adjust miniature size", Colors.ACCENT)),
    ROTATION(Component.text("Adjust miniature rotation", Colors.ACCENT)),
    SAVED(Component.text("Select a saved miniature", Colors.ACCENT));

    private final Component title;

    Page(Component title){
        this.title = title;
    }

    public Component getTitle(){
        return title;
    }

}

