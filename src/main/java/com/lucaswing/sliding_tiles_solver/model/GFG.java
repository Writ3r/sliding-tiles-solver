package com.lucaswing.sliding_tiles_solver.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Java program to check if a given
 * instance of 8 puzzle is solvable or not 
 * https://www.geeksforgeeks.org/check-instance-8-puzzle-solvable/
 * https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
 * 
 * @author Luke
 */
public class GFG 
{  
    // This function returns true
    public static boolean isSolvable(Tile[][] puzzle) 
    { 
        // Count inversions in given 8 puzzle 
        int invCount = getInvCount(puzzle); 

        // return true if inversion count is even. 
        return (invCount % 2 == 0); 
    }
    
    // A utility function to count 
    // inversions in given array 'arr[]' 
    private static int getInvCount(Tile[][] arr) 
    { 
        int inv_count = 0;
        int width = arr[0].length;
        
        for (int i = 0; i < arr[0].length - 1; i++) 
            for (int j = i + 1; j < arr[0].length; j++) 
                // Value 0 is used for empty space 
                if (getFlatPos(arr[j][i].getGoalPos(), width) != arr[0].length * arr[0].length - 1
                        && getFlatPos(arr[j][i].getGoalPos(), width) > getFlatPos(arr[i][j].getGoalPos() ,width)) 
                    inv_count++; 
        
        return inv_count; 
    }
    
    private static int getFlatPos(Position pos, int width) {
        return pos.getX() + pos.getY() * width;
    }
  
} 
