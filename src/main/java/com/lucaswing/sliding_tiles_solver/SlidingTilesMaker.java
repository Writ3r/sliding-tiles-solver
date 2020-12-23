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
import java.util.ArrayList;

import java.util.List;

/**
 * Makes sliding tiles boards
 * @author Luke
 */
public class SlidingTilesMaker {
    
    public static String makeBoard(int width, int numRandMoves) throws InvalidBoardException 
    {
        //build string of board
        List<String> solvedBoardList = new ArrayList();
        for (int i = 1; i < width * width; i ++) {
            solvedBoardList.add(Integer.toString(i));
        }
        solvedBoardList.add(Integer.toString(0));
        String solvedBoardString = String.join(",", solvedBoardList);
        
        //build board and randomly move it
        Board solvedBoard = new Board(solvedBoardString);
        
        for (int i = 0; i < numRandMoves; i ++) {
            int num = getRandomNumber(1, 4);
            solvedBoard.moveX(Direction.values()[num - 1]);
        }
        
        return solvedBoard.toFlatValuesString();
    }
    
    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
}
