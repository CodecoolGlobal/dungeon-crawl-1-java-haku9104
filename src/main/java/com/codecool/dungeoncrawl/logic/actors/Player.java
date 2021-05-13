package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Player extends Actor {

    public boolean hasTorch = false;
    public boolean hasKey = false;
    public ArrayList<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        this.setStrength(5);
        this.setHealth(15);
    }

    public String getTileName() { return "player"; }

    public boolean isHasTorch() { return hasTorch; }

    public void setHasTorch(boolean hasTorch) { this.hasTorch = hasTorch; }

    public String canSee(Cell cell) {
        if (this.hasTorch) {
            return "yes";
        } else {
            if (cell.getX() > this.getX() - 2 && cell.getX() < this.getX() + 2) {
                if (cell.getY() > this.getY() - 6 && cell.getY() < this.getY() + 6) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 7 && cell.getY() < this.getY() + 7) {
                    return "no";
                }
            } else if (cell.getX() > this.getX() - 3 && cell.getX() < this.getX() + 3) {
                if (cell.getY() > this.getY() - 5 && cell.getY() < this.getY() + 5) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 6 && cell.getY() < this.getY() + 6) {
                    return "no";
                }
            } else if (cell.getX() > this.getX() - 4 && cell.getX() < this.getX() + 4) {
                if (cell.getY() > this.getY() - 4 && cell.getY() < this.getY() + 4) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 5 && cell.getY() < this.getY() + 5) {
                    return "no";
                }
            } else if (cell.getX() > this.getX() - 5 && cell.getX() < this.getX() + 5) {
                if (cell.getY() > this.getY() - 3 && cell.getY() < this.getY() + 3) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 4 && cell.getY() < this.getY() + 4 ) {
                    return "no";
                }
            }   else if (cell.getX() > this.getX() - 6 && cell.getX() < this.getX() + 6) {
                if (cell.getY() > this.getY() - 2 && cell.getY() < this.getY() + 2) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 3 && cell.getY() < this.getY() + 3 ) {
                    return "no";
                }
            } else if (cell.getX() > this.getX() - 7 && cell.getX() < this.getX() + 7) {
                if (cell.getY() > this.getY() - 1 && cell.getY() < this.getY() + 1) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 2 && cell.getY() < this.getY() + 2 ) {
                    return "no";
                }
            }
        }
        return "no";
    }

    public void itemPickUp() {
        inventory.add(this.getCell().getItem());
        if (this.getCell().getItem() instanceof Sword) {
            this.setStrength(this.getStrength() + 2);
        } else if (this.getCell().getItem() instanceof Helmet) {
            this.setHealth(this.getHealth() + 2);
        } else if (this.getCell().getItem() instanceof Key) {
            this.setHasKey(true);
        } else if (this.getCell().getItem() instanceof Torch) {
            this.setHasTorch(true);
        }
        this.getCell().setItem(null);
        System.out.println(inventoryToString());
    }

    public String inventoryToString() {
        StringJoiner sj = new StringJoiner("\n");
        for (Item item : inventory) {
            sj.add("* " + item.getTileName());
        }
        return sj.toString();
    }


}
