package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ogre extends Actor{

    public Ogre(Cell cell) {
        super(cell);
        this.setStrength(5);
        this.setHealth(13);
    }

    @Override
    public String getTileName() {
        return "ogre";
    }
}
