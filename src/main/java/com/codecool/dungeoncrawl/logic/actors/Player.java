package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (this.hasTorch) return "yes";
        int playerX = this.getX();
        int playerY = this.getY();
        int cellX = cell.getX();
        int cellY = cell.getY();
        int[] cellCoords = {cellX, cellY};
        int[][] yes = {{playerX, playerY-3}, {playerX, playerY-2}, {playerX, playerY-1}, {playerX, playerY}, {playerX, playerY+1}, {playerX, playerY+2}, {playerX, playerY+3},
                       {playerX-1, playerY-3}, {playerX-1, playerY-2}, {playerX-1, playerY-1}, {playerX-1, playerY}, {playerX-1, playerY+1}, {playerX-1, playerY+2}, {playerX-1, playerY+3},
                       {playerX+1, playerY-3}, {playerX+1, playerY-2}, {playerX+1, playerY-1}, {playerX+1, playerY}, {playerX+1, playerY+1}, {playerX+1, playerY+2}, {playerX+1, playerY+3},
                       {playerX-2, playerY-2}, {playerX-2, playerY-1}, {playerX-2, playerY}, {playerX-2, playerY+1}, {playerX-2, playerY+2},
                       {playerX+2, playerY-2}, {playerX+2, playerY-1}, {playerX+2, playerY}, {playerX+2, playerY+1}, {playerX+2, playerY+2},
                       {playerX-3, playerY-1}, {playerX-3, playerY}, {playerX-3, playerY+1},
                       {playerX+3, playerY-1}, {playerX+3, playerY}, {playerX+3, playerY+1}};
        int[][] little = {{playerX, playerY-4}, {playerX, playerY+4},
                          {playerX-1, playerY-4}, {playerX-1, playerY+4},
                          {playerX+1, playerY-4}, {playerX+1, playerY+4},
                          {playerX-2, playerY-4}, {playerX-2, playerY-3}, {playerX-2, playerY+3}, {playerX-2, playerY+4},
                          {playerX+2, playerY-4}, {playerX+2, playerY-3}, {playerX+2, playerY+3}, {playerX+2, playerY+4},
                          {playerX-3, playerY-3}, {playerX-3, playerY-2}, {playerX-3, playerY+2}, {playerX-3, playerY+3},
                          {playerX+3, playerY-3}, {playerX+3, playerY-2}, {playerX+3, playerY+2}, {playerX+3, playerY+3},
                          {playerX-4, playerY-2}, {playerX-4, playerY-1}, {playerX-4, playerY}, {playerX-4, playerY+1}, {playerX-4, playerY+2},
                          {playerX+4, playerY-2}, {playerX+4, playerY-1}, {playerX+4, playerY}, {playerX+4, playerY+1}, {playerX+4, playerY+2}};
        int[][] hardly = {{playerX, playerY-5}, {playerX, playerY+5},
                          {playerX-1, playerY-5}, {playerX-1, playerY+5},
                          {playerX+1, playerY-5}, {playerX+1, playerY+5},
                          {playerX-2, playerY-5}, {playerX-2, playerY+5},
                          {playerX+2, playerY-5}, {playerX+2, playerY+5},
                          {playerX-3, playerY-5}, {playerX-3, playerY-4}, {playerX-3, playerY+4}, {playerX-3, playerY+5},
                          {playerX+3, playerY-5}, {playerX+3, playerY-4}, {playerX+3, playerY+4}, {playerX+3, playerY+5},
                          {playerX-4, playerY-4}, {playerX-4, playerY-3}, {playerX-4, playerY+3}, {playerX-4, playerY+4},
                          {playerX+4, playerY-4}, {playerX+4, playerY-3}, {playerX+4, playerY+3}, {playerX+4, playerY+4},
                          {playerX-5, playerY-3},{playerX-5, playerY-2}, {playerX-5, playerY-1}, {playerX-5, playerY}, {playerX-5, playerY+1}, {playerX-5, playerY+2}, {playerX+5, playerY-+3},
                          {playerX+5, playerY-3},{playerX+5, playerY-2}, {playerX+5, playerY-1}, {playerX+5, playerY}, {playerX+5, playerY+1}, {playerX+5, playerY+2}, {playerX+5, playerY+3}};
        for (int[] coords : yes) {
            if (Arrays.equals(coords, cellCoords)) return "yes";
        }
        for (int[] coords : little) {
            if (Arrays.equals(coords, cellCoords)) return "little";
        }
        for (int[] coords : hardly) {
            if (Arrays.equals(coords, cellCoords)) return "hardly";
        }
        return "no";
    }


        /*if (this.hasTorch) {
            return "yes";
        } else {
            if (cell.getX() > this.getX() - 2 && cell.getX() < this.getX() + 2) {
                if (cell.getY() > this.getY() - 6 && cell.getY() < this.getY() + 6) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 7 && cell.getY() < this.getY() + 7) {
                    return "hardly";
                }
            } else if (cell.getX() > this.getX() - 3 && cell.getX() < this.getX() + 3) {
                if (cell.getY() > this.getY() - 5 && cell.getY() < this.getY() + 5) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 6 && cell.getY() < this.getY() + 6) {
                    return "hardly";
                }
            } else if (cell.getX() > this.getX() - 4 && cell.getX() < this.getX() + 4) {
                if (cell.getY() > this.getY() - 4 && cell.getY() < this.getY() + 4) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 5 && cell.getY() < this.getY() + 5) {
                    return "hardly";
                }
            } else if (cell.getX() > this.getX() - 5 && cell.getX() < this.getX() + 5) {
                if (cell.getY() > this.getY() - 3 && cell.getY() < this.getY() + 3) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 4 && cell.getY() < this.getY() + 4 ) {
                    return "hardly";
                }
            }   else if (cell.getX() > this.getX() - 6 && cell.getX() < this.getX() + 6) {
                if (cell.getY() > this.getY() - 2 && cell.getY() < this.getY() + 2) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 3 && cell.getY() < this.getY() + 3 ) {
                    return "hardly";
                }
            } else if (cell.getX() > this.getX() - 7 && cell.getX() < this.getX() + 7) {
                if (cell.getY() > this.getY() - 1 && cell.getY() < this.getY() + 1) {
                    return "yes";
                } else if (cell.getY() > this.getY() - 2 && cell.getY() < this.getY() + 2 ) {
                    return "hardly";
                }
            }
        }*/

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
            if (item != null) sj.add("* " + item.getTileName());
        }
        return sj.toString();
    }


}
