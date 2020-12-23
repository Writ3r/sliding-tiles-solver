package com.lucaswing.sliding_tiles_solver.strategy.impl;

import com.lucaswing.sliding_tiles_solver.model.Tile;
import com.lucaswing.sliding_tiles_solver.strategy.Strategy;
import com.lucaswing.sliding_tiles_solver.strategy.StrategyPool;

/**
 * Each side strategy is very similar, so this super class cuts down
 * on some code necessary to run them. They both follow very similar patterns.
 * Future code optimization would be to make a generic side solver that just
 * takes in the side to solve, and solves it.
 * 
 * @author Luke
 * @version 1.0
 */
public abstract class SideStrategy implements Strategy {
    
    /**
     * @param boardState
     * @return whether or not the strategy fits the side
     */
    protected abstract boolean fitsStrat(Tile [][] boardState);
    
    @Override
    public boolean fitsStrategy(Tile [][] boardState) {
        if (checkIfFitsLast6(boardState)) {
            return false;
        } else {
            return fitsStrat(boardState);
        }
    }
    
    private boolean checkIfFitsLast6(Tile[][] boardState) {
        return StrategyPool.getInstance()
                .getStrategy(StrategyPool.LAST6_STRAT)
                .fitsStrategy(boardState);
    }
}
