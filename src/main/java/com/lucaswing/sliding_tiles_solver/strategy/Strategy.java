package com.lucaswing.sliding_tiles_solver.strategy;

import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Tile;

/**
 * Defines an interface for strategies to follow. If a specific board state
 * fits a strategy, then that means the strategy is the correct one to execute for
 * the given state.
 * 
 * @author Luke
 * @version 1.0
 */
public interface Strategy {
    //Executes the strategy
    public void executeStrategy(Board board);

    //Determines if the current state fits the strategy
    public boolean fitsStrategy(Tile [][] boardState);
}
