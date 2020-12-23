package com.lucaswing.sliding_tiles_solver.strategy.impl;

import com.lucaswing.sliding_tiles_solver.aStar.IDDFS;
import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Tile;
import com.lucaswing.sliding_tiles_solver.strategy.Strategy;


/**
 * Strategy to solve the last 6 spaces on a board. There isn't a good 
 * definite pattern I found to be able to do this, but just brute force
 * a Star seems to work well enough. 
 * 
 * @author Luke
 * @version 1.0
 */
public class Last6Strategy implements Strategy {

    @Override
    public void executeStrategy(Board board) {
       IDDFS.executePath(IDDFS.solveBoard(board), board);
    }

    @Override
    public boolean fitsStrategy(Tile [][] boardState) {
        for (int i = 0; i < boardState[0].length; i++) {
            //check top. if break in top, return true.
            for (int x = i; x < boardState[0].length; x++) {
                if (!boardState[i][x].getPos().equals(boardState[i][x].getGoalPos())) {
                    return false;
                }
            }
            if (boardState[0].length - i <= 3) {
                return true;
            }
        }
        return false;
    }

}
