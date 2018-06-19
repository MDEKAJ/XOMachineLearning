package com.uniproject.game.ui;

/**
 * Used to interact with the model for the game.
 */
public interface IGameModel 
{
	/**
	 * Sets the {@link IBoardPanel} via which the board can be controlled.
	 * @param boardPanel The {@link IBoardPanel}.
	 */
	void setBoardPanel(IBoardPanel boardPanel);

	/**
	 * Sets the {@link IStatusPanel} via which the status can be controlled.
	 * @param statusPanel The {@link IStatusPanel}.
	 */
	void setStatusPanel(IStatusPanel statusPanel);
	
	/**
	 * Indicates that a new connect five game should be started.
	 */
	void onNewConnectFiveGame();

	/**
	 * Indicates that a new O-X-O game should be started.
	 */
	void onNewOXOGame();
	
	/**
	 * Indicates that the game should be shutdown.
	 */
	void onExitGame();

	/**
	 * Gets the help text.
	 * @return See above.
	 */
	String getHelpText();

	/**
	 * Gets the name of the game.
	 * @return See above.
	 */
	String getGameName();
	
	/**
	 * Gets the number of rows in the game.
	 * @return See above.
	 */
	int getBoardRowCount();

	/**
	 * Gets the number of columns in the game.
	 * @return See above.
	 */
	int getBoardColumnCount();

	/**
	 * Gets the current player.
	 * @return See above.
	 */
	String getCurrentPlayer();

	/**
	 * Sets the move made by the player.
	 * @param row The move row.
	 * @param column The move column.
	 */
	void setCurrentPlayerMove(int row, int column);

	/**
	 * Indicates that the current player has finished playing their move and it is
	 * now the other players move.
	 */
	void currentPlayerFinishedMove();

	/**
	 * Gets player X.
	 * @return See above.
	 */
	String getPlayerX();
	
	/**
	 * Gets player Y.
	 * @return See above.
	 */
	String getPlayerO();

	void setPlayerXToComputer();

	void setPlayerXToHuman();

	void setPlayerOToComputer();

	void setPlayerOToHuman();

	

}
