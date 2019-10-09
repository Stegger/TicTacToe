/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    private static final int EMPTY_FIELD = -1;
    private static final int BOARD_SIZE = 3;

    private int winner = -1;
    private int nextPlayer = 0;
    private int[][] boardModel = new int[BOARD_SIZE][BOARD_SIZE];

    public GameBoard()
    {
        newGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    @Override
    public int getNextPlayer()
    {
        return nextPlayer;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successful the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    @Override
    public boolean play(int col, int row)
    {
        if (isGameOver() || boardModel[col][row] != EMPTY_FIELD)
        {
            return false;
        }

        boardModel[col][row] = nextPlayer;

        if (hasFoundWinner())
        {
            winner = nextPlayer;
        }
        
        if (nextPlayer == 0)
        {
            nextPlayer = 1;
        } else
        {
            nextPlayer = 0;
        }
        return true;
    }

    @Override
    public boolean isGameOver()
    {
        if (hasFoundWinner() || isDraw())
        {
            return true;
        }
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    @Override
    public int getWinner()
    {
        return winner;
    }

    private boolean isDraw()
    {
        for (int c = 0; c < boardModel.length; c++)
        {
            for (int r = 0; r < boardModel[c].length; r++)
            {
                if (boardModel[c][r] == EMPTY_FIELD)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Resets the game to a new game state.
     */
    @Override
    public void newGame()
    {
        for (int c = 0; c < boardModel.length; c++)
        {
            for (int r = 0; r < boardModel[c].length; r++)
            {
                boardModel[c][r] = EMPTY_FIELD;
            }
        }
        nextPlayer = 0;
        winner = -1;
    }

    private boolean hasFoundWinner()
    {
        //Check rows:
        for (int r = 0; r < BOARD_SIZE; r++)
        {
            if (boardModel[0][r] == boardModel[1][r]
                    && boardModel[1][r] == boardModel[2][r]
                    && boardModel[0][r] != EMPTY_FIELD)
            {
                return true;
            }
        }
        
        //Check columns
        
        //Check diagonals
        
        return false;
    }

}