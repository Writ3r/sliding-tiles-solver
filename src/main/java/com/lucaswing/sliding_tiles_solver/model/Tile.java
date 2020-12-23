package com.lucaswing.sliding_tiles_solver.model;

/**
 * Represents a tile on the board.
 * The tile has a position, a position it wants as a
 * goal, an the ability to be locked/unlocked in its
 * Position.
 * 
 * @author Luke
 * @version 1.0
 */
public class Tile implements Cloneable {

    private Position pos;
    private Position goalPos;
    private boolean isLocked;

    public Tile (Position pos, Position goalPos) {
        this.pos = pos;
        this.goalPos = goalPos;
    }

    @Override
    public Object clone() 
    {
        try {
            Tile t = (Tile) super.clone();
            t.setPos((Position)pos.clone());
            return t; 
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    } 

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Position getGoalPos() {
        return goalPos;
    }

    public void setGoalPos(Position goalPos) {
        this.goalPos = goalPos;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String toString() {
        return "Tile [pos=" + pos + "]";
    }

}
