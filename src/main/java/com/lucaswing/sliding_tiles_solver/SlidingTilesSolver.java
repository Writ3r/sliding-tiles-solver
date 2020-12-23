/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucaswing.sliding_tiles_solver;

import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Direction;
import com.lucaswing.sliding_tiles_solver.model.InvalidBoardException;
import com.lucaswing.sliding_tiles_solver.strategy.Strategy;
import com.lucaswing.sliding_tiles_solver.strategy.StrategyPool;

import java.util.List;

/**
 *
 * @author Luke
 */
public class SlidingTilesSolver {
    
    public static List<Direction> solveTilesPuzzle(String testInput) throws InvalidBoardException {
        return getSolvedBoard(testInput).getMoves();
    }
    
    public static Board getSolvedBoard (String testInput) throws InvalidBoardException 
    {
        Board board = new Board(testInput);
        
        List<Strategy> strats = StrategyPool.getInstance()
                .getAllStrategies();
    	
    	while (!board.isSolved()) {
            for (Strategy s: strats) {
                if (s.fitsStrategy(board.getBoardState())) {
                    s.executeStrategy(board);
                    if (board.isSolved()) {
                        break;
                    }
                }
            }
    	}
    	
    	return board;
    }
    
}
