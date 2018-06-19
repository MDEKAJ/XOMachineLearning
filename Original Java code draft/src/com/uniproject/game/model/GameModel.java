package com.uniproject.game.model;

import com.uniproject.game.ui.IBoardPanel;
import com.uniproject.game.ui.IGameModel;
import com.uniproject.game.ui.IStatusPanel;

/**
 * This class implements the IGameModel which is an interface used to make it easier to interact with the UI side
 * @author tahmoor
 *
 */
public final class GameModel implements IGameModel 
{
	private static final GameModel GAME_MODEL = new GameModel();
	private IBoardPanel boardPanel;
	private IStatusPanel statusPanel;
	private Board board;
	private boolean playerXIsComputer;
	private boolean playerOIsComputer;
	
	private GameModel()
	{
	}
	
	public static GameModel getInstance()
	{
		return GAME_MODEL;
	}
	
	@Override
	public void setBoardPanel(IBoardPanel boardPanel) 
	{
		this.boardPanel = boardPanel;
	}

	@Override
	public void setStatusPanel(IStatusPanel statusPanel) 
	{
		this.statusPanel = statusPanel;
	}

	@Override
	public void onNewConnectFiveGame() 
	{
		board = new Board(15, 5);
	}
	
	@Override
	public void onNewOXOGame() 
	{
		board = new Board(3, 3);
	}
	
	@Override
	public void onExitGame() 
	{
		System.exit(0);
	}

	@Override
	public String getHelpText() 
	{
		return "Get 5 in a row to win!";
	}

	@Override
	public String getGameName() 
	{
		return "Connect 5";
	}

	@Override
	public int getBoardRowCount() 
	{
		return board.getSize();
	}

	@Override
	public int getBoardColumnCount() 
	{
		return board.getSize();
	}

	@Override
	public String getCurrentPlayer() 
	{
		return board.getCurrentPlayer();
	}

	@Override
	public String getPlayerX()
	{
		return board.getPlayerX();
	}
	
	@Override
	public String getPlayerO() 
	{
		return board.getPlayerO();
	}
	
	@Override
	public void setCurrentPlayerMove(int row, int column) 
	{
		board.setMove(row, column);
		if (board.isGameOver())
		{
			if (board.hasWinner() != null)
			{
				statusPanel.setWinner(board.hasWinner());
			}
			else
			{
				statusPanel.setGameEndNoWinner();
			}
		}
	}

	@Override
	public void currentPlayerFinishedMove() 
	{
		board.changePlayer();
		statusPanel.setPlayerTurn(board.getCurrentPlayer());
		if (board.getCurrentPlayer().equals("X") && playerXIsComputer)
		{
			makeComputerMove();
		}
		else if (board.getCurrentPlayer().equals("O") && playerOIsComputer)
		{
			makeComputerMove();
		}
	}

	@Override
	public void setPlayerXToComputer() 
	{
		playerXIsComputer = true;
	}

	@Override
	public void setPlayerXToHuman() 
	{
		playerXIsComputer = false;
	}

	@Override
	public void setPlayerOToComputer() 
	{
		playerOIsComputer = true;
	}

	@Override
	public void setPlayerOToHuman() 
	{
		playerOIsComputer = false;
	}
	
	private void makeComputerMove() 
	{
		Move move = board.minMax(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 2);
		boardPanel.makeMove(move.getX(), move.getY());
	}
	
}
