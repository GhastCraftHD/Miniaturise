package de.leghast.miniaturise.ui;

public enum Page {

    POSITION("§eAdjust miniature position"),
    SIZE("§eAdjust miniature size");

    private String title;

    Page(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}

