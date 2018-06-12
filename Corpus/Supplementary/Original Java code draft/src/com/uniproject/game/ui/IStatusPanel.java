package com.uniproject.game.ui;

public interface IStatusPanel 
{
	/**
	 * Sets the current player.
	 * @param player The current player.
	 */
	void setPlayerTurn(String player);

	/**
	 * Sets the winner.
	 * @param player The winning player.
	 */
	void setWinner(String player);

	/**
	 * Sets the end of the game with no winner.
	 */
	void setGameEndNoWinner();
}
