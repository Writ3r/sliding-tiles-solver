package com.lucaswing.sliding_tiles_solver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the board in sliding tiles.
 * The board has tiles, and the tiles have positions.
 * It keeps track of the current state of the game
 * such as tile locations, especially the X tile location.
 * 
 * @author Luke
 * @version 1.0
 */
public class Board {
	
    private Tile [][] boardState;
    protected int boardWidth;
    private Tile xTile;
    private final List<Direction> moves = new ArrayList<>();

    public Board (String startState) throws InvalidBoardException {

        //build string from board state
        String[] startStateStrArr = startState.split(",");

        //set initial values
        double boardLen = Math.sqrt(startStateStrArr.length);
        
        if (!(boardLen % 1 == 0)) {
            throw new InvalidBoardException("Invalid board entered");
        }
        
        this.boardWidth = (int) boardLen;
        this.boardState = new Tile [boardWidth][boardWidth];
        
        int maxValue = boardWidth * boardWidth;
        
        //fill up board with tiles
        for (int y = 0; y < boardWidth; y++) {
            for (int x = 0; x < boardWidth ; x++) 
            {
                int value = Integer.parseInt(startStateStrArr[(y * boardWidth) + x]) - 1;
                
                if (value > maxValue) {
                    throw new InvalidBoardException("Invalid board entered");
                }

                Position goalPos = (value == -1) ? 
                        new Position(boardWidth - 1, boardWidth - 1) :
                        new Position(value % boardWidth, Math.floorDiv(value, boardWidth));

                boardState[y][x] = new Tile(new Position(x, y), goalPos);

                if (value == -1) {
                    xTile = boardState[y][x];
                }
            }
        }
        
        if (!GFG.isSolvable(boardState)) {
            throw new InvalidBoardException("Invalid board entered");
        }
    }
    
    public Board (Tile [][] startState, Position xTile) {
        this.boardWidth = startState[0].length;
        this.boardState = startState;
        this.xTile = boardState[xTile.getY()][xTile.getX()];
    }

    public boolean moveX(Direction d)
    {
        int xPosX = getXTile().getPos().getX();
        int xPosY = getXTile().getPos().getY();

        switch (d) 
        {
            case U:
                if ((xPosY - 1 < 0)) {
                    return false;
                } else {
                    swapTiles(getXTile(), getBoardState()[xPosY - 1][xPosX]);
                    moves.add(Direction.U);
                }
                break;
            case D:
                if ((xPosY + 1 >= boardWidth)) {
                    return false;
                } else {
                    swapTiles(getXTile(), getBoardState()[xPosY + 1][xPosX]);
                    moves.add(Direction.D);
                }
                break;
            case L:
                if ((xPosX - 1 < 0)) {
                    return false;
                } else {
                    swapTiles(getXTile(), getBoardState()[xPosY][xPosX - 1]);
                    moves.add(Direction.L);
                }
                break;
            case R:
                if ((xPosX + 1 >= boardWidth)) {
                    return false;
                } else {
                    swapTiles(getXTile(), getBoardState()[xPosY][xPosX + 1]);
                    moves.add(Direction.R);
                }
                break;
        }

        System.out.println(toString());

        return true;
    }

    protected void swapTiles(Tile t1, Tile t2) {
        Position t2PosCopy = (Position) t2.getPos().clone();
        getBoardState()[t1.getPos().getY()][t1.getPos().getX()] = t2;
        getBoardState()[t1.getPos().getY()][t1.getPos().getX()].setPos(t1.getPos());
        getBoardState()[t2PosCopy.getY()][t2PosCopy.getX()] = t1;
        getBoardState()[t2PosCopy.getY()][t2PosCopy.getX()].setPos(t2PosCopy);
    }

    public boolean isSolved() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth ; j++) {
                if (!boardState[i][j].getPos().equals(boardState[i][j].getGoalPos())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Tile [][] cloneBoard()
    {
        Tile [][] clonedBoardState = new Tile [boardWidth][boardWidth];

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth ; j++) {
                clonedBoardState[i][j] = (Tile) boardState[i][j].clone();
            }
        }

        return clonedBoardState;
    }

    public Tile getTileFromGoalPos(Position goalPos) {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth ; j++) {
                if (goalPos.equals(boardState[i][j].getGoalPos())) {
                    return boardState[i][j];
                }
            }
        }
        return null;
    }

    public Tile getTileFromPos(Position pos) {
        return boardState[pos.getY()][pos.getX()];
    }

    @Override
    public String toString() 
    {	
        String boardString = "";

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth ; j++) {
                boardString = boardString + 
                        "[ x: " + Integer.toString(boardState[i][j].getGoalPos().getX()) + "]" +
                        "[ y: " + Integer.toString(boardState[i][j].getGoalPos().getY()) + "]";
            }
            boardString =  boardString + "]\n";
        }

        return boardString;
    }
    
    public String toFlatValuesString() 
    {	
        List<String> boardStringList = new ArrayList();

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth ; j++) {
                int flatValue = boardState[i][j].getGoalPos().getX() + boardState[i][j].getGoalPos().getY() * boardWidth;
                boardStringList.add(Integer.toString(flatValue));
            }
        }

        return String.join(",", boardStringList);
    }

    public Tile[][] getBoardState() {
        return boardState;
    }

    public void setBoardState(Tile[][] boardState) {
        this.boardState = boardState;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public Tile getXTile() {
        return xTile;
    }

    public void setXTile(Tile xTile) {
        this.xTile = xTile;
    }

    public List<Direction> getMoves() {
        return moves;
    }
}
