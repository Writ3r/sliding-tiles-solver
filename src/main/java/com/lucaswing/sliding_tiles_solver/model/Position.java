package com.lucaswing.sliding_tiles_solver.model;

/**
 * Represents a Position on the board.
 * Makes it easier to deal with keeping X and Y
 * for tiles, and to do operations with.
 * 
 * @author Luke
 * @version 1.0
 */
public class Position implements Cloneable {
	
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Object clone() { 
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
                return true;
        if (obj == null)
                return false;
        if (getClass() != obj.getClass())
                return false;

        Position other = (Position) obj;
        if (x != other.x)
                return false;
        if (y != other.y)
                return false;

        return true;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
	
}
