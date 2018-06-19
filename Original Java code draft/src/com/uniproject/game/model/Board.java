package com.uniproject.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Board
{
	private static final String O = "O";
	private static final String X = "X";
	private final int size;
	private final int winCount;
	private final String[][] mBoard;
	private static int sTurnCounter = 0;

	// Constructor
	public Board(int size, int winCount)
	{
		this.size = size;
		this.winCount = winCount;
		mBoard = new String[size][size];
	}
	// Constructor
	public Board(Board board)
	{
		this.size = board.getSize();
		this.winCount = board.winCount;
		mBoard = board.mBoard;
	}
	// get players
	public Set<String> getAllPlayers()
	{
		final Set<String> players = new HashSet<String>();
		players.add(X);
		players.add(O);
		return players;
	}
	// get size of board
	public int getSize() 
	{
		return size;
	}
	// change the current player to opponent
	public void changePlayer()
	{
		sTurnCounter++;
	}
	// get the turn it's currently on 
	public int getTurn()
	{
		return sTurnCounter;
	}
	// get the current Player
	public String getCurrentPlayer()
	{
		if (sTurnCounter % 2 == 0)
		{
			return X;
		}
		return O;
	}
	// place a piece on the board
	public void setMove(int row, int column) 
	{
		mBoard[row][column] = getCurrentPlayer();
	}
	// remove a piece on the board 
	public void undoMove(int row, int column)
	{
		mBoard[row][column] = null;
	}
	// get the next empty position on the board (used for a basic AI)
	public int[] getNextEmptyPosition()
	{
		for (int i = 0; i < mBoard.length; i++) 
		{
			for (int j = 0; j < mBoard[i].length; j++) 
			{
				final String move = mBoard[i][j];
				if (move == null)
				{
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
	// check to see if the board is full to know if there are anymore moves 
	public boolean checkBoardIsFull()
	{
		int i = 0;
		for(int x = 0; x < size; x++)
		{
			for(int y = 0; y < size; y++)
			{
				if(mBoard[x][y] != null)
				{
					i++;
				}
			}
		}
		if(i == (size * size))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	// checks if the game is over so if board is full or there is a winner
	public boolean isGameOver()
	{
		if(checkBoardIsFull() == true ||hasWinner() != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// checks for a winner and returns the piece that won 
	public String hasWinner() 
	{
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				String piece = mBoard[x][y];
				if(piece != null)
				{
					if(isColumnWinner(piece, x, y) || isRowWinner(piece, x, y) || isDiagonalDownRightWinner(piece, x, y) || isDiagonalDownLeftWinner(piece, x, y))
					{
						return piece;
					}
				}
			}
		}
		return null;
	}

	public String getPlayerO() 
	{
		return O;
	}

	public String getPlayerX() 
	{
		return X;
	}
	// checks to see if the current piece has won in a diagonal down left position
	private boolean isDiagonalDownLeftWinner(String piece, int x, int y) {
		for(int i = 0; i < winCount; i++)
		{
			int xToCheck = x - i;
			int yToCheck = y + i;
			if (xToCheck < 0 || yToCheck > (size - 1))
			{
				return false;
			}
			String pieceToCheck = mBoard[xToCheck][yToCheck];
			if(pieceToCheck != piece)
			{
				return false;
			}
		}
		return true;
	}
	// checks to see if the current piece has won in a diagonal down right position
	private boolean isDiagonalDownRightWinner(String piece, int x, int y) {
		for(int i = 0; i < winCount; i++)
		{
			int yToCheck = y + i;
			int xToCheck = x + i;
			if (yToCheck > (size - 1) || xToCheck > (size - 1))
			{
				return false;
			}
			String pieceToCheck = mBoard[xToCheck][yToCheck];
			if(pieceToCheck != piece)
			{
				return false;
			}
		}
		return true;
	}
	// check to see if the current piece has won in a row
	private boolean isRowWinner(String piece, int x, int y) {
		for(int i = 0; i < winCount; i++)
		{
			int xToCheck = x + i;
			if (xToCheck > (size - 1))
			{
				return false;
			}
			String pieceToCheck = mBoard[xToCheck][y];
			if(pieceToCheck != piece)
			{
				return false;
			}
		}
		return true;
	}
	// check to see if the current piece has won in a column
	private boolean isColumnWinner(String piece, int x, int y) 
	{
		for(int i = 0; i < winCount; i++)
		{
			int yToCheck = y + i;
			if (yToCheck > (size - 1))
			{
				return false;
			}
			String pieceToCheck = mBoard[x][yToCheck];
			if(pieceToCheck != piece)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Attempt to get the minimax working for both the GOMOKU and TICTACTOE 
	 * @param alpha
	 * @param beta
	 * @param maxDepth
	 * @return
	 */
	public Move minMax(double alpha, double beta, int maxDepth) 
    { 
		ArrayList<Move> moveList = getPossibleMoves();
		Iterator<Move> movesIterator = moveList.iterator();
		Move returnMove; 
		Move bestMove = null;
		
		// evaluate at leaf
		if (maxDepth == 0 || isGameOver())
		{
			Move z = new Move(moveList.get(0).getX(), moveList.get(0).getY());
			z.setValue(evaluate());
			return z;
		}
		if(getCurrentPlayer().equals(getPlayerX()))
		{
			while(movesIterator.hasNext())
			{
				Move currentMove = movesIterator.next();
	            setMove(currentMove.getX(), currentMove.getY());
	            changePlayer();
	            returnMove = minMax(alpha, beta, maxDepth - 1);
	            undoMove(currentMove.getX(), currentMove.getY());
	            if ((bestMove == null) || (bestMove.getValue() < returnMove.getValue())) 
	            {
	                bestMove = returnMove;
	                bestMove = currentMove;
	            }
	            if (returnMove.getValue() > alpha)
	            {
	                alpha = returnMove.getValue();
	                bestMove = returnMove;
	            }
	            if (beta <= alpha) 
	            {
	                bestMove.setValue(beta);
	                bestMove.setX(null);
	                bestMove.setY(null);
	                return bestMove;
	            }
			}
			return bestMove;
		}
		else
		{
			while (movesIterator.hasNext()) 
			{
	            Move currentMove = movesIterator.next();
	            setMove(currentMove.getX(), currentMove.getY());
	            changePlayer();
	            returnMove = minMax(alpha, beta, maxDepth - 1);
	            undoMove(currentMove.getX(), currentMove.getY());
	            if ((bestMove == null) || (bestMove.getValue() > returnMove.getValue())) 
	            {
	                bestMove = returnMove;
	                bestMove = currentMove;
	            }
	            if (returnMove.getValue() < beta) 
	            {
	                beta = returnMove.getValue();
	                bestMove = returnMove;
	            }
	            if (beta <= alpha) 
	            {
	                bestMove.setValue(alpha);
	                bestMove.setX(null);
	                bestMove.setY(null);
	                return bestMove;
	            }
	        }
	        return bestMove;
		}
		
	}
	// an evaluation function to check the current board state
	private double evaluate()
	{
		if(hasWinner() != null)
		{
			if(hasWinner().equals(getPlayerX()))
			{
				return 1000.0;
			}
			else if(hasWinner().equals(getPlayerO()))
			{
				return -1000.0;
			}
		}
		double counter = checkRow() + checkColumn();
		return counter;
	}
	// check the rows to see how many pieces are in a row it'll +10 for X and -10 for O as x is looking for the maximum number possible to win
	private double checkRow()
	{
		double counter = 0.0;
		if(getCurrentPlayer().equals(getPlayerX()))
		{
			for(int x = 0; x < mBoard.length; x++)
			{
				for(int y = 0; y < mBoard.length; y++)
				{
					if(mBoard[x][y] != null)
					{
						if(mBoard[x][y].equals(getPlayerX()))
						{
							for(int i = 0; i < winCount -1; i++)
							{
								int xToCheck = x + i;
								if(xToCheck < size - 1)
								{
									if(mBoard[xToCheck][y] != null)
									{
										if(mBoard[xToCheck][y].equals(getPlayerX()))
										{
											counter = counter + 10.0;
										}
									}
								}
							}
							
						}
					}
				}
			}
		}
		else if(getCurrentPlayer().equals(getPlayerO()))
		{
			for(int x = 0; x < mBoard.length; x++)
			{
				for(int y = 0; y < mBoard.length; y++)
				{
					if(mBoard[x][y] != null)
					{
						if(mBoard[x][y].equals(getPlayerX()))
						{
							for(int i = 0; i < winCount -1; i++)
							{
								int xToCheck = x + i;
								if(xToCheck < size)
								{
									if(mBoard[xToCheck][y] != null)
									{
										if(mBoard[xToCheck][y].equals(getPlayerX()))
										{
											counter = counter - 10.0;
										}
									}
								}
							}
							
						}
					}
				}
			}
		}
		return counter;	
	}
	// check the column to see how many pieces are in a row it'll +10 for X and -10 for O as x is looking for the maximum number possible to win
	private double checkColumn()
	{
		double counter = 0.0;
		if(getCurrentPlayer().equals(getPlayerX()))
		{
			for(int x = 0; x < mBoard.length; x++)
			{
				for(int y = 0; y < mBoard.length; y++)
				{
					if(mBoard[x][y] != null)
					{
						if(mBoard[x][y].equals(getPlayerX()))
						{
							for(int i = 0; i < winCount -1; i++)
							{
								int yToCheck = y + i;
								if(yToCheck < size)
								{
									if(mBoard[x][yToCheck] != null)
									{
										if(mBoard[x][yToCheck].equals(getPlayerX()))
										{
											counter = counter + 10.0;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		else if(getCurrentPlayer().equals(getPlayerO()))
		{
			for(int x = 0; x < mBoard.length; x++)
			{
				for(int y = 0; y < mBoard.length; y++)
				{
					if(mBoard[x][y] != null)
					{
						if(mBoard[x][y].equals(getPlayerX()))
						{
							for(int i = 0; i < winCount -1; i++)
							{
								int yToCheck = y + i;
								if(yToCheck < size)
								{
									if(mBoard[x][yToCheck] != null)
									{
										if(mBoard[x][yToCheck].equals(getPlayerX()))
										{
											counter = counter - 10.0;
										}
									}
								}
							}
							
						}
					}
				}
			}
		}
		return counter;
	}
	
	// get all the moves that you can play 
	public ArrayList<Move> getPossibleMoves()
	{
		final ArrayList<Move> moves = new ArrayList<Move>();
		for(int x = 0; x < mBoard.length; x++)
		{
			for(int y = 0; y < mBoard.length; y++)
			{
				final String checkSpace = mBoard[x][y];
				if(checkSpace == null)
				{
					Move move = new Move(x, y);
					moves.add(move);
				}
			}
		}
		return moves;
	}
	
	@Override
	public String toString() 
	{
		final StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < mBoard.length; i++) 
		{
			for (int j = 0; j < mBoard[i].length; j++) 
			{
				final String move = mBoard[i][j];
				final String valueToAppend = move == null ? "-" : move;
				stringBuilder.append(valueToAppend);
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
