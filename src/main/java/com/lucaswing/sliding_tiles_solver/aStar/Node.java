package com.lucaswing.sliding_tiles_solver.aStar;

import com.lucaswing.sliding_tiles_solver.model.AStarBoard;
import com.lucaswing.sliding_tiles_solver.model.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Represents the current state of the A* game.
 * Each child this creates is a new board state with X
 * moved in a particular direction U, L, R, D
 * 
 * @author Luke
 * @version 1.0
 * 
 */
public class Node {
    
    //the board state this node encapsulates
    private final AStarBoard board;
    
    //the path taken to get to this node
    private final ArrayList<Direction> path = new ArrayList<>();
    
    //boolean to solve whole board, or only find X
    private final boolean solveBoard;
    
    
    public Node(AStarBoard b, ArrayList<Direction> path, boolean solveBoard) {
    	this.board = b;
    	this.path.addAll(path);
    	this.solveBoard = solveBoard;
    }
    
    public boolean isSolved() {
    	if (solveBoard) {
            return board.isSolved();
    	} else {
            return board.isPosSolved();
    	}
    }
    
    public List<Node> getChildren()
    {
        List<Node> nodes = new ArrayList<>();
        Direction lastDirection = path.isEmpty() ? null : path.get(path.size() -1);

        //return board with x up one
        AStarBoard boardUp = board.getBoardWithXMoved(Direction.U);
        if (boardUp != null && lastDirection != Direction.D) {
            ArrayList<Direction> newPath = new ArrayList<>(path);
            newPath.add(Direction.U);
            Node newNode = new Node(boardUp, newPath, solveBoard);
            nodes.add(newNode);
        }

        //return board with x down one
        AStarBoard boardDown = board.getBoardWithXMoved(Direction.D);
        if (boardDown != null && lastDirection != Direction.U) {
            ArrayList<Direction> newPath = new ArrayList<>(path);
            newPath.add(Direction.D);
            Node newNode = new Node(boardDown, newPath, solveBoard);
            nodes.add(newNode);
        }

        //return board with x left one
        AStarBoard boardLeft = board.getBoardWithXMoved(Direction.L);
        if (boardLeft != null && lastDirection != Direction.R) {
            ArrayList<Direction> newPath = new ArrayList<>(path);
            newPath.add(Direction.L);
            Node newNode = new Node(boardLeft, newPath, solveBoard);
            nodes.add(newNode);
        }

        //return board with x right one
        AStarBoard boardRight = board.getBoardWithXMoved(Direction.R);
        if (boardRight != null && lastDirection != Direction.L) {
            ArrayList<Direction> newPath = new ArrayList<>(path);
            newPath.add(Direction.R);
            Node newNode = new Node(boardRight, newPath, solveBoard);
            nodes.add(newNode);
        }

        return nodes;
    }
	
    public AStarBoard getBoard() {
        return board;
    }

    public ArrayList<Direction> getPath() {
        return path;
    }
	
}
