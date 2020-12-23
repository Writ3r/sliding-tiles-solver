package com.lucaswing.sliding_tiles_solver.aStar;

import java.util.List;

/*
* Code based on: https://github.com/ClaasM/Algorithms/blob/master/src/iterative_deepening_dfs/java/simple/IterativeDeepeningDFS.java
* 
* Implementation of iterative deepening DFS (depth-first search).
* Given a start node, this returns the node in the tree below the start node with the target value (or null if it doesn't exist)
* Runs in O(n), where n is the number of nodes in the tree, or O(b^d), where b is the branching factor and d is the depth.
*/
public class IDDFSImpl {
    
    private boolean bottomReached = false;

    public Node iterativeDeepeningDFS(Node start) 
    {
        int depth = 1;
        
        while (!bottomReached) 
        {
            bottomReached = true;
            Node result = iterativeDeepeningDFS(start, 0, depth);
            
            if (result != null) {
            	bottomReached = false;
                return result;
            }

            depth += 1;
            System.out.println("Increasing depth to " + depth);
        }

        return null;
    }

    private Node iterativeDeepeningDFS(Node node, int currentDepth, int maxDepth)
    {
        if (node.isSolved()) {
            return node;
        }
        
        List<Node> children = node.getChildren();

        if (currentDepth == maxDepth) {
            if (children.size() > 0) {
                bottomReached = false;
            }
            return null;
        }

        for (Node child: children) {
            Node result = iterativeDeepeningDFS(child, currentDepth + 1, maxDepth);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}

