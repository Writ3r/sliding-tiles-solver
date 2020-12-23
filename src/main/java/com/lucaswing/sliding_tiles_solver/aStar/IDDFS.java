package com.lucaswing.sliding_tiles_solver.aStar;

import com.lucaswing.sliding_tiles_solver.model.AStarBoard;
import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.Direction;
import com.lucaswing.sliding_tiles_solver.model.Position;

import java.util.ArrayList;

/**
 *
 * Utility Iterative Deepening DFS implementation to find a path from X to the provided position on the
 * provided board. Also allows for finding a path to solve a particular board state.
 * 
 * @author Luke
 * @version 1.0
 * 
 */
public class IDDFS 
{
    public static ArrayList<Direction> findPath(Position pos, Board board) {
    	Node n = new Node(new AStarBoard(board.cloneBoard(), board.getXTile().getPos(), pos), new ArrayList<>(), false);
        IDDFSImpl dfs = new IDDFSImpl();
    	return dfs.iterativeDeepeningDFS(n).getPath();
    }
    
    public static ArrayList<Direction> solveBoard(Board board) {
    	Node n = new Node(new AStarBoard(board.cloneBoard(), board.getXTile().getPos(), null), new ArrayList<>(), true);
        IDDFSImpl dfs = new IDDFSImpl();
    	return dfs.iterativeDeepeningDFS(n).getPath();
    }
    
    public static void executePath (ArrayList<Direction> path, Board board) {
        path.forEach((d) -> {
            board.moveX(d);
        });
    }
}
