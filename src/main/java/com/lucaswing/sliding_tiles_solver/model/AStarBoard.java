package com.lucaswing.sliding_tiles_solver.model;

/**
 * Extension of the board object that allows for easier
 * A* action on the board. Extends the original
 * functionality to allow looking for a path
 * to a desired X position.
 * 
 * @author Luke
 * @version 1.0
 */
public class AStarBoard extends Board implements Cloneable {
    
    private Position desiredXPos;
    
    public AStarBoard(Tile [][] startState, Position xTile, Position desiredPos) {
        super(startState, xTile);
        this.desiredXPos = desiredPos;
    }
    
    public AStarBoard getBoardWithXMoved(Direction d) {

        AStarBoard b = (AStarBoard) clone();

        int xPosX = b.getXTile().getPos().getX();
        int xPosY = b.getXTile().getPos().getY();

        switch (d) 
        {
            case U:
                if ((xPosY - 1 < 0) || b.getBoardState()[xPosY - 1][xPosX].isLocked()) {
                    return null;
                } else {
                     b.swapTilesBoard(b, b.getXTile(), b.getBoardState()[xPosY - 1][xPosX]);
                }
                break;
            case D:
                if ((xPosY + 1 >= boardWidth) || b.getBoardState()[xPosY + 1][xPosX].isLocked()) {
                    return null;
                } else {
                    b.swapTilesBoard(b, b.getXTile(), b.getBoardState()[xPosY + 1][xPosX]);
                }
                break;
            case L:
                if ((xPosX - 1 < 0) || b.getBoardState()[xPosY][xPosX - 1].isLocked()) {
                    return null;
                } else {
                    b.swapTilesBoard(b, b.getXTile(), b.getBoardState()[xPosY][xPosX - 1]);
                }
                break;
            case R:
                if ((xPosX + 1 >= boardWidth) || b.getBoardState()[xPosY][xPosX + 1].isLocked()) {
                    return null;
                } else {
                    b.swapTilesBoard(b, b.getXTile(), b.getBoardState()[xPosY][xPosX + 1]);
                }
                break;
        }

        return b;
    }
    
    @Override
    public Object clone()
    {
        try {
            Board b = (Board) super.clone();
            b.setXTile((Tile) b.getXTile().clone());
            b.setBoardState(cloneBoard());
            return b; 
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void swapTilesBoard(AStarBoard b, Tile t1, Tile t2) {
        Position t2PosCopy = (Position) t2.getPos().clone();
        b.getBoardState()[t1.getPos().getY()][t1.getPos().getX()] = t2;
        b.getBoardState()[t1.getPos().getY()][t1.getPos().getX()].setPos(t1.getPos());
        b.getBoardState()[t2PosCopy.getY()][t2PosCopy.getX()] = t1;
        b.getBoardState()[t2PosCopy.getY()][t2PosCopy.getX()].setPos(t2PosCopy);
    }
    
    public boolean isPosSolved() {
        return desiredXPos.equals(getXTile().getPos());
    }

    public void setDesiredXPos(Position desiredXPos) {
        this.desiredXPos = desiredXPos;
    }
    
}
