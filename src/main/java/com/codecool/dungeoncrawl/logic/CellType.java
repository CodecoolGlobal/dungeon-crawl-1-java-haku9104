package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    CLOSEDDOOR("closedDoor"),
    OPENEDDOOR("openedDoor"),
    STAIRS("stairs"),
    CANTSEE("cantSee"),
    GRAVE("grave"),
    TREE("tree"),
    FENCE("fence"),
    WIFE("wife");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
