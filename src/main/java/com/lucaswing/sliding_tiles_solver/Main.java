package com.lucaswing.sliding_tiles_solver;

import com.lucaswing.sliding_tiles_solver.model.Direction;
import com.lucaswing.sliding_tiles_solver.model.InvalidBoardException;

import java.util.List;

public class Main {
    
    /*
    * Takes in a board in arg 0.
    * Boards are in the form "0,3,2,1"
    * Where 0 is the X, whitespace on the board
    */
    public static void main( String[] args ) throws InvalidBoardException
    {
        List<Direction> solvedDirections = SlidingTilesSolver.solveTilesPuzzle(args[0]);
    	
    	System.out.println("Number of moves: " + solvedDirections.size());
    	System.out.println("Moves: " + solvedDirections.toString());	
    }
}
