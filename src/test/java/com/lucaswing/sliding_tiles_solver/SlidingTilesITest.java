package com.lucaswing.sliding_tiles_solver;

import com.lucaswing.sliding_tiles_solver.model.Board;
import com.lucaswing.sliding_tiles_solver.model.InvalidBoardException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Integration test for whole functionality.
 * Should also make unit tests as well
 * 
 * @author Luke
 */
public class SlidingTilesITest {
    
    String size7Test =  "44,30,9,5,17,21,26,"
            +           "6,1,24,29,4,47,16,"
            +           "15,8,42,28,11,12,13,"
            +           "37,18,3,2,25,22,48,"
            +           "32,36,38,7,14,10,41,"
            +           "19,40,43,39,34,20,33,"
            +           "46,23,45,0,31,27,35";
    
    
    String size5Test1 = "2,6,3,7,10,"
            +           "13,1,8,5,4,"
            +           "16,11,22,15,20,"
            +           "0,17,14,9,24,"
            +           "12,18,19,21,23";
    
    String size5Test2 = "1,11,3,12,5,"
            +           "7,0,16,10,8,"
            +           "2,23,17,4,9,"
            +           "14,21,22,15,6,"
            +           "18,19,13,24,20";
    
    String size5Test3 = "1,9,2,13,15,"
            +           "6,4,8,14,5,"
            +           "11,12,3,0,22,"
            +           "16,20,7,10,24,"
            +           "21,18,23,17,19";
    
    String size5Test4 = "6,2,13,12,1,"
            +           "7,11,14,3,5,"
            +           "16,8,0,19,4,"
            +           "22,23,20,18,24,"
            +           "17,21,15,10,9";
    
    
    String size3Test1 = "4,3,1"
            +           ",2,8,6,"
            +           "0,7,5";
    
    String size3Test2 = "1,6,5,"
            +           "7,3,8,"
            +           "0,4,2";
    
    
    String size2Test1 = "0,3,"
            +           "2,1";
    
   @Test
   public void testReasonableSizes() throws InvalidBoardException 
   {
        Board b;
        
        //solve 7x7 board
        b = SlidingTilesSolver.getSolvedBoard(size7Test);
        Assert.assertTrue(b.isSolved());
        
        //solve 5x5 board
        b = SlidingTilesSolver.getSolvedBoard(size5Test1);
        Assert.assertTrue(b.isSolved());
        b = SlidingTilesSolver.getSolvedBoard(size5Test2);
         Assert.assertTrue(b.isSolved());
        b = SlidingTilesSolver.getSolvedBoard(size5Test3);
        Assert.assertTrue(b.isSolved());
        b = SlidingTilesSolver.getSolvedBoard(size5Test4);
        Assert.assertTrue(b.isSolved());
        
        //solve 3x3 board
        b = SlidingTilesSolver.getSolvedBoard(size3Test1);
        Assert.assertTrue(b.isSolved());
        b = SlidingTilesSolver.getSolvedBoard(size3Test2);
        Assert.assertTrue(b.isSolved());
        
        //solve 2x2 board
        b = SlidingTilesSolver.getSolvedBoard(size2Test1);
        Assert.assertTrue(b.isSolved());
   }
   
   @Test(expected = InvalidBoardException.class)
   public void testBadBoardInvalidNumber() throws InvalidBoardException
   {
        String badBoard = "0,54,"
            +             "2,1";

        //test bad board
        Board b = SlidingTilesSolver.getSolvedBoard(badBoard);
        Assert.assertTrue(!b.isSolved());
   }
   
   @Test(expected = InvalidBoardException.class)
   public void testBadBoardInvalidInputSize() throws InvalidBoardException
   {
        String badBoard = "0,3,4,"
            +             "2,1";

        //test bad board
        Board b = SlidingTilesSolver.getSolvedBoard(badBoard);
        Assert.assertTrue(!b.isSolved());
   }
   
   @Test
   public void testMakeBoard() throws InvalidBoardException
   {
        //test bad board
        //String board = SlidingTilesMaker.makeBoard(7, 30000);
        
        //System.out.println(board);
        
        //Board b = SlidingTilesSolver.getSolvedBoard(board);
        //Assert.assertTrue(b.isSolved());
   }
    
}
