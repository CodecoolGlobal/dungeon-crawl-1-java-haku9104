package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Torch extends Item{

    public Torch(Cell cell) { super(cell); }

    @Override
    public String getTileName() {
        return "torch";
    }

}
