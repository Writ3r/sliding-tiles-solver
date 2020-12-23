package com.lucaswing.sliding_tiles_solver.strategy.impl;

import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Position;
import com.lucaswing.sliding_tiles_solver.model.Tile;
import com.lucaswing.sliding_tiles_solver.strategy.action.MoveTileToPositionAction;

/**
 * Strategy to solve the left col of the board. This runs after
 * the top of the board is solved. 
 * 
 * @author Luke
 * @version 1.0
 */
public class FillLeftStrategy extends SideStrategy {

    @Override
    public void executeStrategy(Board board)
    {
        Tile [][] boardState = board.getBoardState();
        
        int width = boardState[0].length;
        Position endpoint = findEndpoint(boardState);
        Position currGoal = boardState[endpoint.getY()][endpoint.getX()].getPos();
        
        //special case for last 2 tiles in row
        Position fillLeftUntilPoint = new Position(endpoint.getX(), width - 3);
        Position leftFill1 = new Position(endpoint.getX(), width - 1);
        Position leftFill2 = new Position(endpoint.getX() + 1, width - 1);
        
        //lock all tiles before the endpoint. Inefficient, but devising a better way might make this too complex for now.
        for (int x = 0; x <= endpoint.getX(); x++) {
            int lockEndpont = x == endpoint.getX() ? endpoint.getY() : width;
            for (int y = 0; y < lockEndpont; y++) {
                board.getTileFromPos(new Position(x, y)).setLocked(true);
            }
        }
        
        //execute actions to solve board up to last 2 tiles
        for (int y = endpoint.getY(); y <= fillLeftUntilPoint.getY(); y++) {
            currGoal = new Position(currGoal.getX(), y);
            MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(currGoal),currGoal);
            board.getTileFromPos(currGoal).setLocked(true);
        }

        //move tiles out of the way so can't get stuck
        for (int y = fillLeftUntilPoint.getY() + 1; y < width; y++) {
            Position posToCheck = new Position(endpoint.getX(), y);
            if (board.getTileFromPos(posToCheck).getGoalPos().getX() == endpoint.getX()) {
                MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(posToCheck),new Position(width - 1, y));
            }
        }
        
        //get last two tiles into their assigned positions.
        MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(new Position(endpoint.getX(), width - 2)),leftFill1);
        board.getTileFromPos(leftFill1).setLocked(true);
        MoveTileToPositionAction.executeAction(board, board.getTileFromGoalPos(new Position(endpoint.getX(), width - 1)),leftFill2);
        board.getTileFromPos(leftFill2).setLocked(true);
        
        //move in the correct manner to solve the last 2 tiles
        MoveTileToPositionAction.executeAction(board, board.getXTile(), new Position(endpoint.getX(), width - 2));
        board.getTileFromPos(leftFill1).setLocked(false);
        MoveTileToPositionAction.executeAction(board, board.getXTile(), leftFill1);
        board.getTileFromPos(leftFill2).setLocked(false);
        MoveTileToPositionAction.executeAction(board, board.getXTile(), leftFill2);
        
        //ensure line is locked. Could optimize this.
        lockYLine(endpoint.getX(), board.getBoardState());
    }
	
    private Position findEndpoint(Tile [][] boardState) {
        for (int y = 0; y < boardState[0].length; y++) {
           for (int x = 0; x < boardState[0].length; x++) {
                if (!boardState[x][y].getPos().equals(boardState[x][y].getGoalPos())) {
                    return boardState[x][y].getPos();
                }
           }
        }
        return null;
    }

    private  void lockYLine(int x, Tile [][] boardState) {
        for (int y = 0; y < boardState[0].length; y++) {
            boardState[y][x].setLocked(true);
        }
    }
    
    @Override
    protected boolean fitsStrat(Tile [][] boardState) {
        for (int i = 0; i < boardState[0].length; i++) {
            //check top. if break in top, return true.
            for (int x = i; x < boardState[0].length; x++) {
                if (!boardState[i][x].getPos().equals(boardState[i][x].getGoalPos())) {
                    return false;
                }
            }
            //check left. if break in left, return true.
            for (int y = i; y < boardState[0].length; y++) {
                if (!boardState[y][i].getPos().equals(boardState[y][i].getGoalPos())) {
                    return true;
                }
            }
        }
        return false;
    }

}
