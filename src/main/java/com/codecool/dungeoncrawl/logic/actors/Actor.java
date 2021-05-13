package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.sql.SQLOutput;

public abstract class Actor implements Drawable {
    private String name;
    private Cell cell;
    private int health;
    private int strength;
    private boolean hasWon = false;
    private boolean hasKey = false;
    private boolean isAlive = true;
    private boolean canMove;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public boolean isNextMapComing(){
        if(cell.getType() == CellType.STAIRS) return true;
        else return false;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (this.name != null && (this.name.equals("Gergo") || this.name.equals("Martin") || this.name.equals("Balazs")) && nextCell.getActor() == null && nextCell.getType() != CellType.WIFE) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if ((nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.OPENEDDOOR || nextCell.getType() == CellType.STAIRS) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getActor() != null) {
            if (cell.getActor() instanceof Player) {
                this.fightMonster(nextCell.getActor());
            }
        } else if (nextCell.getType() == CellType.CLOSEDDOOR && cell.getActor().isHasKey()) {
            nextCell.setType(CellType.OPENEDDOOR);
        } else if (nextCell.getType() == CellType.WIFE) {
            setHasWon(true);
        }
    }

    private void fightMonster(Actor actor) {
        actor.setHealth(actor.getHealth() - this.getStrength());
        if (actor.getHealth() > 0) {
            this.setHealth(this.getHealth() - actor.getStrength());
            if (this.getHealth() < 1) this.setAlive(false);
        } else {
            actor.getCell().setActor(null);
        }
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isAlive() { return isAlive; }

    public void setAlive(boolean alive) { isAlive = alive; }

    public int getHealth() { return health; }

    public void setHealth(int health) { this.health = health; }

    public Cell getCell() { return cell; }

    public int getX() { return cell.getX(); }

    public int getY() { return cell.getY(); }

    public boolean isHasWon() { return hasWon; }

    public void setHasWon(boolean hasWon) { this.hasWon = hasWon; }

    public void setHasKey(boolean hasKey) { this.hasKey = hasKey; }

    public boolean isHasKey() { return hasKey; }

    public boolean isCanMove() { return canMove; }

    public void setCanMove(boolean canMove) { this.canMove = canMove; }

    public int getStrength() { return strength; }

    public void setStrength(int strength) { this.strength = strength; }
}
