package com.lucaswing.sliding_tiles_solver.strategy.impl;

import com.lucaswing.sliding_tiles_solver.aStar.IDDFS;
import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Tile;
import com.lucaswing.sliding_tiles_solver.strategy.Strategy;


/**
 * Strategy to solve the last 4 spaces on a board. 
 * Only useful on 2x2 boards. 
 * Why would someone solve a 2x2 board? lol.
 * 
 * @author Luke
 * @version 1.0
 */
public class Last4Strategy implements Strategy {

    @Override
    public void executeStrategy(Board board) {
       IDDFS.executePath(IDDFS.solveBoard(board), board);
    }

    @Override
    public boolean fitsStrategy(Tile [][] boardState) {
        return boardState[0].length == 2;
    }

}
