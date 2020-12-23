package com.lucaswing.sliding_tiles_solver.strategy.action;

import com.lucaswing.sliding_tiles_solver.aStar.IDDFS;
import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Position;
import com.lucaswing.sliding_tiles_solver.model.Tile;

/**
 * Moves a tile to the target location on the board.
 * Uses a* to find the optimal path to do so without
 * walking on locked tiles.
 * 
 * @author Luke
 * @version 1.0
 */
public class MoveTileToPositionAction {
    
    /*
    * Executes the movement action
    */
    public static void executeAction(Board board, Tile tileToMove, Position targLoc) {

        while (!tileToMove.getPos().equals(targLoc)) 
        {
            //lock the tile to move so a* doesn't path through it
            tileToMove.setLocked(true);
            
            //Run DFS to find a path in front of the tile
            IDDFS.executePath(IDDFS.findPath(calculateDragPos(board, tileToMove, targLoc), board), board);
            
            //Unlock it so we can move through it
            tileToMove.setLocked(false);

            //move past the tileToMove
            IDDFS.executePath(IDDFS.findPath(tileToMove.getPos(), board), board);
        }
    }
    
    /*
    * Finds the location in line with the target pos to drag the tile from
    */
    private static Position calculateDragPos(Board board, Tile tileToMove, Position targLoc) 
    {
        if (targLoc.getX() != tileToMove.getPos().getX()) {
            if (tileToMove.getPos().getX() > targLoc.getX()) {
                Position possiblePos = new Position(tileToMove.getPos().getX() - 1, tileToMove.getPos().getY());
                if (!board.getTileFromPos(possiblePos).isLocked()) {
                    return possiblePos;
                }
            } else {
                Position possiblePos = new Position(tileToMove.getPos().getX() + 1, tileToMove.getPos().getY());
                if (!board.getTileFromPos(possiblePos).isLocked()) {
                    return possiblePos;
                }
            }
        }

        if (targLoc.getY() != tileToMove.getPos().getY()) {
            if (tileToMove.getPos().getY() > targLoc.getY()) {
                Position possiblePos = new Position(tileToMove.getPos().getX(), tileToMove.getPos().getY() - 1);
                if (!board.getTileFromPos(possiblePos).isLocked()) {
                    return possiblePos;
                }
            } else {
                Position possiblePos = new Position(tileToMove.getPos().getX(), tileToMove.getPos().getY() + 1);
                if (!board.getTileFromPos(possiblePos).isLocked()) {
                    return possiblePos;
                }
            }
        }

        return null;
    }
	
}
