package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {

    public Ghost(Cell cell) {
        super(cell);
        this.setHealth(13);
        this.setStrength(4);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
