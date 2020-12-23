package com.lucaswing.sliding_tiles_solver.strategy.impl;

import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Position;
import com.lucaswing.sliding_tiles_solver.model.Tile;
import com.lucaswing.sliding_tiles_solver.strategy.action.MoveTileToPositionAction;

/**
 * Strategy to solve the top row of the board. This must be solved before
 * the left side is solved to follow the algorithm to solve the board.
 * 
 * @author Luke
 * @version 1.0
 */
public class FillTopStrategy extends SideStrategy {
	
    @Override
    public void executeStrategy(Board board) 
    {
        Tile [][] boardState = board.getBoardState();
        
        int width = boardState[0].length;
        Position endpoint = findEndpoint(boardState);
        Position currGoal = boardState[endpoint.getY()][endpoint.getX()].getPos();
        
        //special case for last 2 tiles in row
        Position fillTopUntilPoint = new Position(width - 3, endpoint.getY());
        Position topFill1 = new Position(width - 1, endpoint.getY());
        Position topFill2 = new Position(width - 1, endpoint.getY() + 1);
        
        //lock all tiles before the endpoint. Inefficient, but devising a better way might make this too complex for now.
        for (int y = 0; y <= endpoint.getY(); y++) {
            int lockEndpont = y == endpoint.getY() ? endpoint.getX() : width;
            for (int x = 0; x < lockEndpont; x++) {
                board.getTileFromPos(new Position(x, y)).setLocked(true);
            }
        }
        
        //execute actions to solve board up to last 2 tiles
        for (int x = endpoint.getX(); x <= fillTopUntilPoint.getX(); x++) {
            currGoal = new Position(x, currGoal.getY());
            MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(currGoal),currGoal);
            board.getTileFromPos(currGoal).setLocked(true);
        }

        //move tiles out of the way so can't get stuck
        for (int x = fillTopUntilPoint.getX() + 1; x < width; x++) {
            Position posToCheck = new Position(x, endpoint.getY());
            if (board.getTileFromPos(posToCheck).getGoalPos().getY() == endpoint.getY()) {
                MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(posToCheck),new Position(x, width - 1));
            }
        }

        //get last two tiles into their assigned positions.
        MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(new Position(width - 2, endpoint.getY())),topFill1);
        board.getTileFromPos(topFill1).setLocked(true);
        MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(new Position(width - 1, endpoint.getY())),topFill2);
        board.getTileFromPos(topFill2).setLocked(true);

        //move in the correct manner to solve the last 2 tiles
        MoveTileToPositionAction.executeAction(board, board.getXTile(), new Position(width - 2, endpoint.getY()));
        board.getTileFromPos(topFill1).setLocked(false);
        MoveTileToPositionAction.executeAction(board, board.getXTile(), topFill1);
        board.getTileFromPos(topFill2).setLocked(false);
        MoveTileToPositionAction.executeAction(board, board.getXTile(), topFill2);
        
        //ensure line is locked. Could optimize this.
        lockXLine(endpoint.getY(), board.getBoardState());
    }

    @Override
    protected boolean fitsStrat(Tile [][] boardState) {
        for (int i = 0; i < boardState[0].length; i++) {
            //check top. if break in top, return true.
            for (int x = i; x < boardState[0].length; x++) {
                if (!boardState[i][x].getPos().equals(boardState[i][x].getGoalPos())) {
                    return true;
                }
            }
            //check left. if break in left, return true.
            for (int y = i; y < boardState[0].length; y++) {
                if (!boardState[y][i].getPos().equals(boardState[y][i].getGoalPos())) {
                    return false;
                }
            }
        }
        return false;
    }

    private Position findEndpoint(Tile [][] boardState) {
        for (int y = 0; y < boardState[0].length; y++) {
            for (int x = 0; x < boardState[0].length; x++) {
                if (!boardState[y][x].getPos().equals(boardState[y][x].getGoalPos())) {
                    return boardState[y][x].getPos();
                }
            }
        }
        return null;
    }

    private  void lockXLine(int y, Tile [][] boardState) {
        for (int x = 0; x < boardState[0].length; x++) {
            boardState[y][x].setLocked(true);
        }
    }

}
